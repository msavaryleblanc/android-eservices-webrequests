package android.eservices.webrequests.data.repository.bookdisplay;

import android.eservices.webrequests.data.api.model.Departement;
import android.eservices.webrequests.data.api.model.DepartementSearchResponse;
import android.eservices.webrequests.data.entity.DepartementEntity;
import android.eservices.webrequests.data.repository.bookdisplay.local.DepartementDisplayLocalDataSource;
import android.eservices.webrequests.data.repository.bookdisplay.mapper.DepartementToDepartementEntityMapper;
import android.eservices.webrequests.data.repository.bookdisplay.remote.DepartementDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class DepartementDisplayDataRepository implements DepartementDisplayRepository {
    private DepartementDisplayLocalDataSource departementDisplayLocalDataSource;
    private DepartementDisplayRemoteDataSource departementDisplayRemoteDataSource;
    private DepartementToDepartementEntityMapper departementToDepartementEntityMapper;

    public DepartementDisplayDataRepository(DepartementDisplayLocalDataSource departementDisplayLocalDataSource,
                                            DepartementDisplayRemoteDataSource departementDisplayRemoteDataSource,
                                            DepartementToDepartementEntityMapper departementToDepartementEntityMapper) {
        this.departementDisplayLocalDataSource = departementDisplayLocalDataSource;
        this.departementDisplayRemoteDataSource = departementDisplayRemoteDataSource;
        this.departementToDepartementEntityMapper = departementToDepartementEntityMapper;
    }

    @Override
    public Single<DepartementSearchResponse> getDepartementSearchResponse(String keywords) {
        return departementDisplayRemoteDataSource.getDepartementSearchResponse()
                .zipWith(departementDisplayLocalDataSource.getFavoriteIdList(), new BiFunction<DepartementSearchResponse, List<String>, DepartementSearchResponse>() {
                    @Override
                    public DepartementSearchResponse apply(DepartementSearchResponse departementSearchResponse, List<String> idList) throws Exception {
                        for (Departement departement : departementSearchResponse.getDepartementList()) {
                            if (idList.contains(departement.getId())) {
                                departement.setFavorite();
                            }
                        }
                        return departementSearchResponse;
                    }
                });
    }

    @Override
    public Flowable<List<DepartementEntity>> getFavoriteDepartement() {
        return departementDisplayLocalDataSource.loadFavorites();
    }

    @Override
    public Completable addDepartementToFavorites(String departementId) {
        return departementDisplayRemoteDataSource.getDepartementDetails(departementId)
                .map(new Function<Departement, DepartementEntity>() {
                    @Override
                    public DepartementEntity apply(Departement departement) throws Exception {
                        return departementToDepartementEntityMapper.map(departement);
                    }
                })
                .flatMapCompletable(new Function<DepartementEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(DepartementEntity departementEntity) throws Exception {
                        return departementDisplayLocalDataSource.addDepartementToFavorites(departementEntity);
                    }
                });
    }

    @Override
    public Completable removeDepartementFromFavorites(String departementId) {
        return departementDisplayLocalDataSource.deleteDepartementFromFavorites(departementId);
    }
}
