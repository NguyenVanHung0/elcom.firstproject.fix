
package com.elcom.management_library_data.elasticsearch.model;

import java.util.Date;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "book")
public class BookElas {
    
    @Id
    private String id;
    
    @Field(type = FieldType.Text, name = "created_by")
    private String createdBy;
    
    @Field(type = FieldType.Text, name = "modified_by")
    private String modifiedBy;
    
    @Field(type = FieldType.Date, name = "created_date")
    private Date createdDate;
    
    @Field(type = FieldType.Date, name = "modified_date")
    private Date modifiedDate;
    
    @Field(type = FieldType.Text, name = "name")
    private String name;
    
    @Field(type = FieldType.Text, name = "nhaxb")
    private String nhaXB;
    
    @Field(type = FieldType.Text, name = "namxb")
    private String namXB;
    
    @Field(type = FieldType.Text, name = "first_text")
    private String firstText;
    
    
    @Field(type = FieldType.Long, name = "author_id")
    private Long authorId;
    
    
    @Field(type = FieldType.Long, name = "category_id")
    private Long categoriId;
}
