package com.mgiandia.library.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mgiandia.library.R;
import com.mgiandia.library.view.reservation.BookSearchActivity;

public class BookReservationActivity extends AppCompatActivity implements BookReservationView {

    private EditText edtTitle;
    private EditText edtAuthor;
    private Button btnSearch;
    private BookReservationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_reservation2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new BookReservationPresenter(this);

        edtTitle = findViewById(R.id.edt_book_title);
        edtAuthor = findViewById(R.id.edt_author);
        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.search(edtTitle.getText().toString(), edtAuthor.getText().toString());
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void showError(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSearchView(String title, String author) {

        Intent intent = new Intent(this, BookSearchActivity.class);
        intent.putExtra("BOOK_TITLE_EXTRA", title);
        intent.putExtra("BOOK_AUTHOR_EXTRA", author);
        startActivityForResult(intent, 1);

    }

    @Override
    public void showSearchResult(String bookDescription) {

    }

}
