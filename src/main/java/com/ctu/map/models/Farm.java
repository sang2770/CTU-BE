package com.ctu.map.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Getter
@Setter
@NoArgsConstructor  // Tạo constructor không tham số
@AllArgsConstructor // Tạo constructor có tất cả tham số
@Table(name = "farms")
public class Farm {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String coordinates;

    @Version
    private Long version;
}
