package com.ioffeivan.courses.core.ui.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ioffeivan.courses.core.model.Course
import com.ioffeivan.courses.core.ui.R
import com.ioffeivan.courses.core.ui.databinding.ItemCourseBinding

class CoursesAdapter(
    private val listener: Listener,
) : ListAdapter<Course, CoursesAdapter.CourseViewHolder>(CourseItemCallback()) {

    class CourseViewHolder(
        val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            with(binding) {
                textViewRate.text = course.rate
                textViewStartDate.text = course.startDate
                textViewTitle.text = course.title
                textViewDescription.text = course.text
                textViewPrice.text = root.context.getString(R.string.currency, course.price)
                if (course.hasLike)
                    imageButtonBookmark.setImageResource(R.drawable.ic_bookmark_fill)
                else
                    imageButtonBookmark.setImageResource(R.drawable.ic_bookmark)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.binding.imageButtonBookmark.setOnClickListener {
            listener.onClick(course)
        }
        holder.bind(course)
    }

    interface Listener {
        fun onClick(course: Course)
    }
}

class CourseItemCallback : DiffUtil.ItemCallback<Course>() {

    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}