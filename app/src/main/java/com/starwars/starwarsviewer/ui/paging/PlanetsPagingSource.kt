package com.starwars.starwarsviewer.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.starwars.starwarsviewer.domain.planet.model.Planet
import com.starwars.starwarsviewer.domain.planet.usecase.GetPlanetPageUseCase
import retrofit2.HttpException
import java.io.IOException

class PlanetsPagingSource(private val getPlanetPageUseCase: GetPlanetPageUseCase) :
    PagingSource<Int, Planet>() {

    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planet> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = getPlanetPageUseCase.getPlanetPage(nextPageNumber)

            return LoadResult.Page(
                data = response.results,
                prevKey = if (response.previous != null) nextPageNumber.minus(1) else response.previous,
                nextKey = if (response.next != null) nextPageNumber.plus(1) else response.next
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}