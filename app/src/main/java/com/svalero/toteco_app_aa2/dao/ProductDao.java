package com.svalero.toteco_app_aa2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products WHERE publication_id = :publicationId")
    List<ProductLocal> findByPublicationId(int publicationId);

    @Query("SELECT * FROM products WHERE id = :id")
    ProductLocal findById(int id);

    @Query("SELECT * FROM products")
    List<ProductLocal> findAll();

//    @Transaction
//    @Query("SELECT * FROM publications WHERE id = (SELECT publication_id FROM products WHERE id = :id)")
//    PublicationWithProduct findPublicationByProductId(int id);

    @Insert
    void insert(ProductLocal productLocal);

    @Update
    void update(ProductLocal productLocal);

    @Delete
    void delete(ProductLocal productLocal);

    @Query("DELETE FROM products WHERE publication_id = :publication_id")
    void deleteByPublicationId(int publication_id);
}
