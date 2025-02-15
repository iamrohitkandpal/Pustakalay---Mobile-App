package com.pustakalay.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.pustakalay.R;
import com.pustakalay.adapters.BookAdapter;
import com.pustakalay.databinding.FragmentBooksBinding;
import com.pustakalay.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksFragment extends Fragment {
    private FragmentBooksBinding binding;
    private BookAdapter adapter;
    private List<Book> bookList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBooksBinding.inflate(inflater, container, false);
        setupBookList();
        setupSearch();
        return binding.getRoot();
    }

    private void setupBookList() {
        // Sample data
        bookList.add(new Book("Android Development", "John Doe", "Available", R.drawable.ic_book));
        bookList.add(new Book("Java Programming", "Jane Smith", "Borrowed", R.drawable.ic_book));
        
        adapter = new BookAdapter(bookList, book -> {
            // Handle book click
            if(book.getStatus().equals("Available")) {
                showBorrowDialog(book);
            } else {
                Toast.makeText(getContext(), "Book not available", Toast.LENGTH_SHORT).show();
            }
        });
        
        binding.rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvBooks.setAdapter(adapter);
    }

    private void setupSearch() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
            // ... other overrides
        });
    }

    private void filter(String text) {
        List<Book> filteredList = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(book);
            }
        }
        adapter.filterList(filteredList);
    }

    private void showBorrowDialog(Book book) {
        new AlertDialog.Builder(getContext())
            .setTitle("Borrow Book")
            .setMessage("Borrow " + book.getTitle() + "?")
            .setPositiveButton("Confirm", (dialog, which) -> {
                book.setStatus("Borrowed");
                adapter.notifyDataSetChanged();
            })
            .setNegativeButton("Cancel", null)
            .show();
    }
} 