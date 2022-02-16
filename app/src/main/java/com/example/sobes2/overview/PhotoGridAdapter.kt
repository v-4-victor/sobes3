/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.sobes2.overview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sobes2.NewsData
import com.example.sobes2.Results
import com.example.sobes2.databinding.NewsItemBinding

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class PhotoGridAdapter( private val onClickListener: OnClickListener) :
        ListAdapter<NewsData,
                PhotoGridAdapter.ResultsViewHolder>(DiffCallback) {

    /**
     * The ResultsViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [Results] information.
     */
    class ResultsViewHolder(private var binding: NewsItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: NewsData) {
            binding.property = marsProperty
            Log.d("ERROR", marsProperty.title)
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Results]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<NewsData>() {
        override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
            return oldItem.title == newItem.title
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ResultsViewHolder {
        return ResultsViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Results]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Results]
     */
    class OnClickListener(val clickListener: (marsProperty: NewsData) -> Unit) {
        fun onClick(marsProperty: NewsData) = clickListener(marsProperty)
    }
}

