import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faiz.patanistaticui.data.Repository
import com.faiz.patanistaticui.ui.screen.cart.CartViewModel
import com.faiz.patanistaticui.ui.screen.category.CategoryViewModel
import com.faiz.patanistaticui.ui.screen.detailCategory.DetailCategoryViewModel
import com.faiz.patanistaticui.ui.screen.detailProduct.DetailProductViewModel
import com.faiz.patanistaticui.ui.screen.home.HomeViewModel
import com.faiz.patanistaticui.ui.screen.login.LoginViewModel
import com.faiz.patanistaticui.ui.screen.orderlist.OrderListViewModel
import com.faiz.patanistaticui.ui.screen.profile.ProfileViewModel
import com.faiz.patanistaticui.ui.screen.signin.SigninViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(SigninViewModel::class.java)) {
            return SigninViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailCategoryViewModel::class.java)) {
            return DetailCategoryViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(OrderListViewModel::class.java)) {
            return OrderListViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailProductViewModel::class.java)) {
            return DetailProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}