package com.nbsp.materialfilepicker.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbsp.materialfilepicker.R;
import com.nbsp.materialfilepicker.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Dimorinny on 24.10.15.
 */

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.DirectoryViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class DirectoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView mFileImage;
        private TextView mFileTite;
        private TextView mFileSubtitle;

        public DirectoryViewHolder(View itemView, final OnItemClickListener clickListener) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, getAdapterPosition());
                }
            });

            mFileImage = (ImageView) itemView.findViewById(R.id.item_file_image);
            mFileTite = (TextView) itemView.findViewById(R.id.item_file_title);
            mFileSubtitle = (TextView) itemView.findViewById(R.id.item_file_subtitle);
        }
    }

    private List<File> mFiles;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public DirectoryAdapter(Context context, List<File> files) {
        mContext = context;
        mFiles = files;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_file, parent, false);

        return new DirectoryViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {
        File currentFile = mFiles.get(position);

        holder.mFileImage.setImageDrawable(ContextCompat.getDrawable(mContext,
                currentFile.isDirectory() ? R.drawable.ic_folder_gray_48dp : R.drawable.ic_file_gray_48dp));
        holder.mFileSubtitle.setText(
                currentFile.isDirectory() ? mContext.getString(R.string.catalog) : FileUtils.getReadableFileSize(currentFile.length()));
        holder.mFileTite.setText(currentFile.getName());
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public File getModel(int index) {
        return mFiles.get(index);
    }
}