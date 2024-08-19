package com.example.appbanvexemphim.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanvexemphim.DAO.ReviewDAO;
import com.example.appbanvexemphim.Model.Review;
import com.example.appbanvexemphim.R;

import java.util.List;

public class ReviewAdminActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Review> adapter;
    private ReviewDAO reviewDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_review);

        listView = findViewById(R.id.listViewReviews);
        reviewDAO = new ReviewDAO(this);

        final List<Review>[] reviewList = new List[]{reviewDAO.getAll()}; // Sử dụng phương thức DAO để lấy danh sách đánh giá
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reviewList[0]); // Adapter cho ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Review selectedReview = reviewList[0].get(position); // Đánh giá được chọn
                reviewDAO.deleteReview(selectedReview.getId()); // Xóa đánh giá
                Toast.makeText(getApplicationContext(), "Đánh giá đã được xóa", Toast.LENGTH_SHORT).show();

                // Cập nhật lại danh sách đánh giá
                reviewList[0] = reviewDAO.getAll(); // Lấy lại danh sách đánh giá
                adapter.clear();
                adapter.addAll(reviewList[0]); // Làm mới adapter
                adapter.notifyDataSetChanged(); // Cập nhật ListView
            }
        });
    }
}
