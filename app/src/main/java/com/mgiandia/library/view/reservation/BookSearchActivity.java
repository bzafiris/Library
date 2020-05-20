package com.mgiandia.library.view.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mgiandia.library.R;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.view.reservation.fragment.BookListFragment;

import java.util.Set;

import static com.mgiandia.library.view.reservation.BookReservationActivity.AUTHOR_NAME_EXTRA;
import static com.mgiandia.library.view.reservation.BookReservationActivity.BOOK_TITLE_EXTRA;

public class BookSearchActivity extends AppCompatActivity implements BookListFragment.OnListFragmentInteractionListener {

    public static final String BOOK_ID_EXTRA = "book_id_extra";
    BookSearchViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        Intent intent = getIntent();
        String titleCriterion = intent.getStringExtra(BOOK_TITLE_EXTRA);
        String authorCriterion = intent.getStringExtra(AUTHOR_NAME_EXTRA);

        Log.d("BookSearchActivity", "Search criteria: " + titleCriterion
                + " " + authorCriterion);

        viewModel = new ViewModelProvider(this).get(BookSearchViewModel.class);

        if (findViewById(R.id.fragment_container) != null){

            // Activity is recreated, do not add fragment twice
            if (savedInstanceState != null){
                return;
            }

            viewModel.getPresenter().searchBooks(titleCriterion, authorCriterion);

            BookListFragment bookListFragment = BookListFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, bookListFragment)
                    .commit();
        }

    }

    @Override
    public void onListFragmentInteraction(Book item) {
        Intent intent = new Intent();
        intent.putExtra(BOOK_ID_EXTRA, item.getId());
        setResult(RESULT_OK, intent);
        onBackPressed();
    }

    @Override
    public Set<Book> getBookList() {
        return viewModel.getPresenter().getResults();
    }
}
