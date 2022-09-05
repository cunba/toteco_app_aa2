package com.svalero.toteco_app_aa2.api;

import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.Menu;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.ProductType;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.User;
import com.svalero.toteco_app_aa2.domain.dto.EstablishmentDTO;
import com.svalero.toteco_app_aa2.domain.dto.MenuDTO;
import com.svalero.toteco_app_aa2.domain.dto.PasswordChangeDTO;
import com.svalero.toteco_app_aa2.domain.dto.ProductDTO;
import com.svalero.toteco_app_aa2.domain.dto.ProductTypeDTO;
import com.svalero.toteco_app_aa2.domain.dto.PublicationDTO;
import com.svalero.toteco_app_aa2.domain.dto.UserDTO;
import com.svalero.toteco_app_aa2.domain.login.JwtRequest;
import com.svalero.toteco_app_aa2.domain.login.JwtResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TotecoApiInterface {

    // ESTABLISHMENTS

    @GET("establishments")
    Call<List<Establishment>> getAllEstablishments();

    @GET("establishments/{id}")
    Call<Establishment> getEstablishmentById(@Path("id") int id);

    @GET("establishments/name/{name}")
    Call<List<Establishment>> getEstablishmentsByName(@Path("name") String name);

    @GET("establishments/date/{date}")
    Call<List<Establishment>> getEstablishmentsByCreationDate(@Path("date") long date);

    @GET("establishments/date/between")
    Call<List<Establishment>> getEstablishmentsByCreationDateBetween(@Query("minDate") long minDate,
                                                                     @Query("maxDate") long maxDate);

    @GET("establishments/open/{open}")
    Call<List<Establishment>> getEstablishmentsByOpen(@Path("open") boolean open);

    @GET("establishments/punctuation/{punctuation}")
    Call<List<Establishment>> getEstablishmentsByPunctuation(@Path("punctuation") float punctuation);

    @GET("establishments/punctuation/between")
    Call<List<Establishment>> getEstablishmentsByPunctuationBetween(@Query("minPunctuation") float minPunctuation,
                                                                    @Query("maxPunctuation") float maxPunctuation);

    @POST("establishments")
    Call<Establishment> createEstablishment(@Header("Authorization") String authorization, @Body EstablishmentDTO establishmentDTO);

    @PUT("establishments/{id}")
    Call<Establishment> updateEstablishment(@Header("Authorization") String authorization, @Body EstablishmentDTO establishment, @Path("id") int id);

    @PATCH("establishments/{id}/punctuation")
    Call<String> updateEstablishmentsPunctuation(@Header("Authorization") String authorization, @Path("id") int id);

    @DELETE("establishments/{id}")
    Call<String> deleteEstablishment(@Header("Authorization") String authorization, @Path("id") int id);

    @DELETE("establishments")
    Call<String> deleteAllEstablishments(@Header("Authorization") String authorization);


    // PRODUCTS

    @GET("products")
    Call<List<Product>> getAllProducts();

    @GET("products/{id}")
    Call<List<Product>> getProductById(@Path("id") int id);

    @GET("products/date/{date}")
    Call<List<Product>> getProductsByDate(@Path("date") long date);

    @GET("products/date/between")
    Call<List<Product>> getProductsByDateBetween(@Query("minDate") long minDate, @Query("maxDate") long maxDate);

    @GET("products/inMenu/{inMenu}")
    Call<List<Product>> getProductsByInMenu(@Path("inMenu") boolean inMenu);

    @GET("products/price/{price}")
    Call<List<Product>> getProductsByPrice(@Path("price") float price);

    @GET("products/price/between")
    Call<List<Product>> getProductsByPriceBetween(@Query("minPrice") float minPrice, @Query("maxPrice") float maxPrice);

    @GET("products/punctuation/{punctuation}")
    Call<List<Product>> getProductsByPunctuation(@Path("punctuation") float punctuation);

    @GET("products/punctuation/between")
    Call<List<Product>> getProductsByPunctuationBetween(@Query("minPunctuation") float minPunctuation,
                                                        @Query("maxPunctuation") float maxPunctuation);

    @GET("products/type/{id}")
    Call<List<Product>> getProductsByTypeId(@Path("id") int typeId);

    @GET("products/menu/{id}")
    Call<List<Product>> getProductsByMenuId(@Path("id") int menuId);

    @GET("products/publication/{id}")
    Call<List<Product>> getProductsByPublicationId(@Path("id") int publicationId);

    @POST("products")
    Call<Product> createProduct(@Header("Authorization") String authorization, @Body ProductDTO productDTO);

    @PUT("products/{id}")
    Call<Product> updateProduct(@Header("Authorization") String authorization, @Body ProductDTO productDTO, @Path("id") int id);

    @PATCH("products/{id}/price/{price}")
    Call<String> updateProductPrice(@Header("Authorization") String authorization, @Path("id") int id, @Path("price") float price);

    @PATCH("products/{id}/punctuation/{punctuation}")
    Call<String> updateProductsPunctuation(@Header("Authorization") String authorization, @Path("id") int id, @Path("punctuation") float punctuation);

    @DELETE("products/{id}")
    Call<String> deleteProduct(@Header("Authorization") String authorization, @Path("id") int id);

    @DELETE("products")
    Call<String> deleteAllProducts(@Header("Authorization") String authorization);


    // PRODUCT TYPES

    @GET("types")
    Call<List<ProductType>> getAllProductTypes();

    @GET("types/{id}")
    Call<ProductType> getProductTypeById(@Path("id") int id);

    @GET("types/name/{name}")
    Call<List<ProductType>> getProductTypesByName(@Path("name") String name);

    @GET("types/type/{type}")
    Call<List<ProductType>> getProductTypesByType(@Path("type") String type);

    @GET("types/name/{name}/type/{type}")
    Call<List<ProductType>> getProductTypesByNameAndType(@Path("name") String name, @Path("type") String type);


    // PUBLICATIONS

    @GET("publications")
    Call<List<Publication>> getAllPublications();

    @GET("publications/{id}")
    Call<Publication> getPublicationById(@Path("id") int id);

    @GET("publications/date/{date}")
    Call<List<Publication>> getPublicationsByDate(@Path("date") long date);

    @GET("publications/date/between")
    Call<List<Publication>> getPublicationsByDateBetween(@Query("minDate") long minDate,
                                                         @Query("maxDate") long maxDate);

    @GET("publications/price/{price}")
    Call<List<Publication>> getPublicationsByPrice(@Path("price") float price);

    @GET("publications/price/between")
    Call<List<Publication>> getPublicationsByPriceBetween(@Query("minPrice") float minPrice,
                                                          @Query("maxPrice") float maxPrice);

    @GET("publications/punctuation/{punctuation}")
    Call<List<Publication>> getPublicationsByPunctuation(@Path("punctuation") float punctuation);

    @GET("publications/punctuation/between")
    Call<List<Publication>> getPublicationsByPunctuationBetween(@Query("minPunctuation") float minPunctuation,
                                                                @Query("maxPunctuation") float maxPunctuation);

    @GET("publications/establishment/{id}")
    Call<List<Publication>> getPublicationsByEstablishmentId(@Path("id") int establishmentId);

    @GET("publications/user/{id}")
    Call<List<Publication>> getPublicationsByUserId(@Path("id") int userId);

    @GET("publications/type/{type}")
    Call<List<Publication>> getPublicationsByType(@Path("type") String type);

    @GET("publications/date/price/punctuation/between")
    Call<List<Publication>> getPublicationsByDateAndPriceAndPunctuationBetween(@Query("minDate") long minDate,
                                                                               @Query("maxDate") long maxDate,
                                                                               @Query("minPrice") float minPrice,
                                                                               @Query("maxPrice") float maxPrice,
                                                                               @Query("minPunctuation") float minPunctuation,
                                                                               @Query("maxPunctuation") float maxPunctuation);

    @POST("publications")
    Call<Publication> createPublication(@Header("Authorization") String authorization, @Body PublicationDTO publicationDTO);

    @PUT("publications/{id}")
    Call<Publication> updatePublication(@Header("Authorization") String authorization, @Body PublicationDTO publicationDTO, @Path("id") int id);

    @DELETE("publications/{id}")
    Call<String> deletePublication(@Header("Authorization") String authorization, @Path("id") int id);

    @DELETE("publications")
    Call<String> deleteAllPublications(@Header("Authorization") String authorization);


    // USERS

    @GET("users")
    Call<List<User>> getAllUsers(@Header("Authorization") String authorization);

    @GET("users/{id}")
    Call<User> getUserById(@Header("Authorization") String authorization, @Path("id") int id);

    @GET("user-loged")
    Call<User> getUserLoged(@Header("Authorization") String authorization);

    @POST("users")
    Call<User> createUser(@Body UserDTO userDTO);

    @PATCH("users/{id}/publications-number")
    Call<String> updateUserPublicationsNumber(@Header("Authorization") String authorization, @Path("id") int id);

    @PATCH("users/{id}/money-spent")
    Call<String> updateUserMoneySpent(@Header("Authorization") String authorization, @Path("id") int id);

    @PATCH("users/{id}/password")
    Call<String> updateUserPassword(@Header("Authorization") String authorization, @Path("id") int id, @Body PasswordChangeDTO passwordChangeDTO);

    @PATCH("users/{id}/disable")
    Call<String> disableUser(@Header("Authorization") String authorization, @Path("id") int id);

    @PATCH("users/{id}/activate")
    Call<String> activateUser(@Header("Authorization") String authorization, @Path("id") int id);


    // LOGIN

    @POST("login")
    Call<JwtResponse> login(@Body JwtRequest jwtRequest);

}
