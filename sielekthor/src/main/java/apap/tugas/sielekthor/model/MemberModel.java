package apap.tugas.sielekthor.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "member")
public class MemberModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Integer jenisKelamin;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String namaMember;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(nullable = false)
    private Date tanggalPendaftaran;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date tanggalLahir;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PembelianModel> listPembelian;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNamaMember() {
        return namaMember;
    }

    public void setNamaMember(String namaMember) {
        this.namaMember = namaMember;
    }

    public Date getTanggalPendaftaran() {
        return tanggalPendaftaran;
    }

    public void setTanggalPendaftaran(Date tanggalPendaftaran) {
        this.tanggalPendaftaran = tanggalPendaftaran;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public List<PembelianModel> getListPembelian() {
        return listPembelian;
    }

    public void setListPembelian(List<PembelianModel> listPembelian) {
        this.listPembelian = listPembelian;
    }



}