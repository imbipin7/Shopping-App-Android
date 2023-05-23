package com.bipin.shopy.utils

//import com.hbb20.CountryCodePicker
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bipin.shopy.MainActivity
import com.bipin.shopy.genericadapters.SpinnerGenericAdapter
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mukesh.mukeshotpview.completeListener.MukeshOtpCompleteListener
import com.mukesh.mukeshotpview.mukeshOtpView.MukeshOtpView


/** Binding Adapters */
object BindingAdapters {


    @BindingAdapter(
        value = ["setViewPager2", "isUserInputEnabled", "currentPosition", "showNext", "setDots"],
        requireAll = false
    )
    @JvmStatic
    fun setViewPagerAdapter(
        viewPager2: ViewPager2?,
        adapter: RecyclerView.Adapter<*>,
        isUserInputEnabled: Boolean?,
        currentPosition: Int = 0,
        showNext: Boolean? = false,
        tabLayout: TabLayout?,
    ) {
        viewPager2?.adapter = adapter
        viewPager2?.currentItem = currentPosition
        isUserInputEnabled?.let {
            viewPager2?.isUserInputEnabled = it
        }
        if (tabLayout != null) {
            if (viewPager2 != null) {
                TabLayoutMediator(tabLayout, viewPager2) { _, _ ->
                }.attach()
            }
        }
        if (showNext == true) {
            viewPager2?.apply {
                setPageTransformer(CardSliderTransformer(viewPager2))
                clipToPadding = false
                setPadding(20, 0, 20, 0)
                addItemDecoration(PageDecoration(space = 20f))
            }
        }
/*        if (pageIndicatorView != null) {
            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    pageIndicatorView.selection = position
                }
            })
        }*/
    }

    @BindingAdapter(value = ["setPaddingHorizontal", "setPaddingVertical"], requireAll = true)
    @JvmStatic
    fun setPaddingHorizontal(view: View, paddingHorizontal: Float?, paddingVertical: Float?) {
        view.setPadding(
            paddingHorizontal?.toInt() ?: 0,
            paddingVertical?.toInt() ?: 0,
            paddingHorizontal?.toInt() ?: 0,
            paddingVertical?.toInt() ?: 0
        )
    }


    @BindingAdapter(value = ["addPaddingToView", "paddingToAdd"], requireAll = false)
    @JvmStatic
    fun addPaddingToView(
        recyclerView: View,
        boolean: Boolean,
        paddingToAdd: Int,
    ) {
        if (boolean)
            recyclerView.setPadding(0, 0, 0, paddingToAdd)

    }


    @BindingAdapter(value = ["spinnerAdapter"], requireAll = false)
    @JvmStatic
    fun spinnerAdapter(
        spinner: Spinner,
        adapter: SpinnerGenericAdapter<*>,
    ) {
        spinner.adapter = adapter
    }


    @BindingAdapter(value = ["bottomNavigationListener"], requireAll = false)
    @JvmStatic
    fun bottomNavigationListener(
        bottomNavigationView: BottomNavigationView,
        listener: NavigationBarView.OnItemSelectedListener,
    ) {
        bottomNavigationView.setOnItemSelectedListener(listener)
    }

    @BindingAdapter(value = ["onCheckChange"], requireAll = false)
    @JvmStatic
    fun onCheckChange(
        compoundButton: CompoundButton,
        listener: CompoundButton.OnCheckedChangeListener,
    ) {
        compoundButton.setOnCheckedChangeListener(listener)
    }

    @BindingAdapter(value = ["setImageUrl", "placeHolder"], requireAll = false)
    @JvmStatic
    fun setImageUrl(
        imageView: ImageView,
        url: String?,
        placeHolder: Drawable? = null,
    ) {
        when {
            url.isNullOrEmpty() && placeHolder == null -> return
            url.isNullOrEmpty() && placeHolder != null -> Glide.with(imageView).load(placeHolder)
                .into(imageView)
            !url.isNullOrEmpty() && placeHolder == null -> Glide.with(imageView).load(url)
                .into(imageView)
            !url.isNullOrEmpty() && placeHolder != null -> Glide.with(imageView).load(url)
                .placeholder(placeHolder).error(placeHolder).into(imageView)
        }
    }

    @BindingAdapter(value = ["setBackground"], requireAll = false)
    @JvmStatic
    fun setBackground(
        view: View,
        drawable: Int?,
    ) {
        if (drawable != null)
            view.background = ContextCompat.getDrawable(view.context, drawable)
        else {
            if (view is AppCompatEditText)
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        view.context,
                        android.R.color.transparent
                    )
                )
        }
    }

    @BindingAdapter(value = ["radioGroupListener"], requireAll = false)
    @JvmStatic
    fun radioGroupListener(
        view: RadioGroup,
        listener: RadioGroup.OnCheckedChangeListener,
    ) {
        view.setOnCheckedChangeListener(listener)
    }

    @BindingAdapter(value = ["addTextWatcher"], requireAll = false)
    @JvmStatic
    fun addTextWatcher(
        view: EditText,
        listener: TextWatcher,
    ) {
        view.addTextChangedListener(listener)
    }

/*    @BindingAdapter(value = ["ccpValue"], requireAll = false)
    @JvmStatic
    fun setPicker(ccp: CountryCodePicker, observeValue: ObservableField<String>) {
        try {
            if (observeValue.get() == null)
                return
            ccp.setDefaultCountryUsingNameCode("IN")
            if (observeValue.get()?.isNotEmpty() == false) {
                observeValue.set(ccp.defaultCountryCodeWithPlus)
            } else {
                if (observeValue.get()?.contains("+") == true) {
                    val temp = observeValue.get()!!.replace("+", "")
                    ccp.setCountryForPhoneCode(temp.toInt())
                } else {
                    ccp.setCountryForPhoneCode(observeValue.get()!!.toInt())
                }
            }
            ccp.setOnCountryChangeListener { observeValue.set(ccp.selectedCountryCodeWithPlus) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/


    @BindingAdapter(value = ["addScrollListener"], requireAll = false)
    @JvmStatic
    fun addScrollListener(
        recyclerView: RecyclerView,
        listener: RecyclerView.OnScrollListener
    ) {
        recyclerView.addOnScrollListener(listener)
    }

    @BindingAdapter(
        value = ["setRecyclerAdapter", "rvScrollListener", "chipLayout", "rvSwipe"],
        requireAll = false
    )
    @JvmStatic
    fun setRecyclerAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<*>?,
        listener: RecyclerView.OnScrollListener?,
        chipLayout: Boolean?,
        swipeListener: ItemTouchHelper.SimpleCallback?,
//        scrollToBottom: ObservableField<Boolean>?,
    ) {
        recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        adapter?.let {
            recyclerView.adapter = it
        }

        listener?.let { recyclerView.addOnScrollListener(listener) }

        if (chipLayout == true) {
            val layoutManager = FlexboxLayoutManager(MainActivity.context?.get())
            layoutManager.flexWrap = FlexWrap.WRAP
            layoutManager.flexDirection = FlexDirection.ROW
            recyclerView.layoutManager = layoutManager
        }
        swipeListener?.let {
            val checker = ItemTouchHelper(it)
            checker.attachToRecyclerView(recyclerView)
        }
    }

    @BindingAdapter(value = ["otpListener"], requireAll = false)
    @JvmStatic
    fun otpListener(
        otpView: MukeshOtpView,
        listener: MukeshOtpCompleteListener
    ) {
        otpView.setOtpCompletionListener(listener)
    }

    @BindingAdapter(value = ["disableSeek"], requireAll = false)
    @JvmStatic
    fun disableSeek(
        seekBar: SeekBar,
        boolean: Boolean
    ) {
        seekBar.isEnabled = boolean
    }

    @BindingAdapter(value = ["setSeekbarProgress"], requireAll = false)
    @JvmStatic
    fun startTimer(
        seekBar: SeekBar,
        progress: ObservableField<Int>
    ) {
        if (seekBar.progress == progress.get())
            return

        seekBar.progress = progress.get() ?: 0
        seekBar.isEnabled = false
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }
}