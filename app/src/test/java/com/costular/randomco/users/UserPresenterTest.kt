package com.costular.randomco.users

import com.costular.randomco.data.User
import com.costular.randomco.data.source.UsersRepository
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.junit.Assert.*

/**
 * Created by costular on 15/07/17.
 */
class UserPresenterTest {

    val view: UsersContract.View = mock()
    val repository: UsersRepository = mock()

    lateinit var presenter: UsersPresenter

    val successCaptor = argumentCaptor<(List<User>) -> Unit>()
    val errorCaptor = argumentCaptor<(String) -> Unit>()

    @Before
    fun setup() {
        presenter = UsersPresenter(view, repository)
    }

    @Test
    fun testLoadUsersFromAPI() {
        // Load from API
        whenever(presenter.load(true))

        // Check set loading
        verify(view.setLoading(true))

        // Check if repository has requested the data
        verify(repository).getUsers(successCaptor.capture(), errorCaptor.capture())

        // Retrieve the users
        var users: List<User> = arrayListOf()
        successCaptor.firstValue.invoke(users)

        // Check the size
        assertTrue(users.size >= 10)
    }
}