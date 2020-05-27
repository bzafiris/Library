package com.mgiandia.library.view.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.mgiandia.library.R;
import com.mgiandia.library.view.framework.BaseActivity;

public class BookReservationActivity extends BaseActivity<BookReservationViewModel>
        implements BookReservationView {

    public static final String BOOK_TITLE_EXTRA = "book_title";
    public static final String AUTHOR_NAME_EXTRA = "author_name";
    public static final int REQUEST_CODE_BOOK_SEARCH = 1;
    Button btnSearchBook;
    EditText edtBookTitle;
    EditText edtAuthorName;
    Button btnReserveBook;
    EditText edtBorrowerId;
    TextView txtReservationStatus;
    TextView txtBookInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_reservation);

        final BookReservationPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        // Πρόσβαση σε αντικείμενα της διεπαφής χρήστη
        btnSearchBook = findViewById(R.id.btn_search_book);
        btnReserveBook = findViewById(R.id.btn_reserve_book);
        edtBookTitle = findViewById(R.id.edt_book_title);
        edtAuthorName = findViewById(R.id.edt_author_name);
        edtBorrowerId = findViewById(R.id.edt_borrower_id);
        txtReservationStatus = findViewById(R.id.txt_reservation_status);
        txtBookInfo = findViewById(R.id.txt_book_info);

        // Αρχικοποίηση στοιχείων UI
        txtReservationStatus.setText("");

        // Ανάθεση χειριστών γεγονότων (click listeners)
        btnSearchBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookTitle = edtBookTitle.getText().toString();
                String authorName = edtAuthorName.getText().toString();
                presenter.search(bookTitle, authorName);
            }
        });

        btnReserveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String borrowerId = edtBorrowerId.getText().toString();
                presenter.submitReservationRequest(borrowerId);
            }
        });

    }

    @Override
    protected BookReservationViewModel getViewModel() {
        return new ViewModelProvider(this).get(BookReservationViewModel.class);
    }

    /**
     * Handle the result from BookSearchActivity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_BOOK_SEARCH){
            if (resultCode == RESULT_OK){
                int bookId = data.getIntExtra(BookSearchActivity.BOOK_ID_EXTRA, -1);
                viewModel.getPresenter().setSearchResult(bookId);
            }
        }
    }

    @Override
    public void showStatus(String msg) {
        txtReservationStatus.setText(msg);
    }

    @Override
    public void showSearchView(String title, String author) {
        Intent intent = new Intent(this, BookSearchActivity.class);
        intent.putExtra(BOOK_TITLE_EXTRA, title);
        intent.putExtra(AUTHOR_NAME_EXTRA, author);
        startActivityForResult(intent, REQUEST_CODE_BOOK_SEARCH);
    }

    @Override
    public void showSearchResult(String bookDescription) {
        txtBookInfo.setText(bookDescription);
    }
}

