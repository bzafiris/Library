package com.mgiandia.library.view.reservation;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mgiandia.library.R;
import com.mgiandia.library.domain.Book;

import java.util.ArrayList;
import java.util.Set;

public class BookSearchActivity extends AppCompatActivity
        implements ItemSelectionListener<Book>, BookSearchView {

    public static final String BOOK_ID_EXTRA = "book_id";

    RecyclerView recyclerView;
    private BookAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private BookSearchPresenter bookSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        Intent intent = getIntent();

        // extract search criteria from intent
        String title = intent.getStringExtra(BookReservationActivity.BOOK_TITLE_EXTRA);
        String authorName = intent.getStringExtra(BookReservationActivity.AUTHOR_NAME_EXTRA);
        // find search result

        BookSearchViewModel model = new ViewModelProvider(this).get(BookSearchViewModel.class);
        bookSearchPresenter = model.getPresenter();
        bookSearchPresenter.setView(this);


        Set<Book> result = bookSearchPresenter.searchBooks(title, authorName);

        // Update UI with the result

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new BookAdapter(new ArrayList<Book>(result));
        recyclerView.setAdapter(mAdapter);
        // register the current activity as listener for book selection events
        mAdapter.setBookSelectionListener(this);
    }

    /**
     * The method will be called by the adapter, whenever the user clicks on a list item
     * @param item
     *
     *
     */
    @Override
    public void onItemSelected(Book item) {
        bookSearchPresenter.onBookSelected(item);
    }

    @Override
    public void returnSearchResult(int id) {
        // return result to calling Activity
        Intent intent = new Intent();
        intent.putExtra(BOOK_ID_EXTRA, id);
        setResult(RESULT_OK, intent);
        // close activity
        finish();
    }
}
