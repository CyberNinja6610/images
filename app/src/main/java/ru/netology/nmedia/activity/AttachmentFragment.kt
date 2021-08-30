package ru.netology.nmedia.activity

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentAttachmentBinding
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.view.load

class AttachmentFragment : Fragment() {

    companion object {
        var Bundle.urlArg: String? by StringArg
    }

    private var fragmentBinding: FragmentAttachmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val activity = requireActivity()
        if (activity is AppActivity) {
            activity.supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireActivity(),R.color.black)))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attachment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.close -> {
                findNavController().navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAttachmentBinding.inflate(
            inflater,
            container,
            false
        )
        fragmentBinding = binding

        arguments?.urlArg
            ?.let(binding.attachmentImage::load)

        return binding.root
    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
        val activity = requireActivity()
        if (activity is AppActivity) {
            activity.supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireActivity(),R.color.colorPrimary)))
        }
    }
}