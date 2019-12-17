package com.sample.musicapp.domain.usecases

import com.sample.musicapp.domain.entities.SearchResult
import com.sample.musicapp.domain.entities.SearchResultItem
import com.sample.musicapp.domain.repositories.SearchRepository
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

class SearchUseCaseImplTest {

    /**
     * Test if albums sorted correctly
     */
    @Test
    fun execute_verify() {
        // Init sample data
        val query = "album"
        val searchResultItem1 = SearchResultItem(1, "b")
        val searchResultItem2 = SearchResultItem(2, "c")
        val searchResultItem3 = SearchResultItem(3, "a")
        val searchResult = SearchResult(
            resultCount = 3,
            results = listOf(searchResultItem1, searchResultItem2, searchResultItem3)
        )
        val searchResultAlbumsSortedCorrectly = SearchResult(
            resultCount = 3,
            results = listOf(searchResultItem3, searchResultItem1, searchResultItem2)
        )
        val searchResultAlbumsSortedIncorrectly = SearchResult(
            resultCount = 3,
            results = listOf(searchResultItem2, searchResultItem3, searchResultItem1)
        )
        // Mock needed classes
        val repository: SearchRepository = Mockito.mock(SearchRepository::class.java)
        Mockito.doReturn(Single.just(searchResult)).`when`(repository).search(query)
        // Init tested class
        val searchUseCase = SearchUseCaseImpl(repository)

        // Test
        searchUseCase.execute(query)
            .test()
            .assertValue {
                it == searchResultAlbumsSortedCorrectly
            }
            .assertNever {
                it == searchResultAlbumsSortedIncorrectly
            }
            .dispose()
    }
}