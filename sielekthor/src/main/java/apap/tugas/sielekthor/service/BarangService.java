package apap.tugas.sielekthor.service;
import apap.tugas.sielekthor.model.BarangModel;
import java.util.List;
import org.springframework.stereotype.Service;

public interface BarangService {
    void addBarang(BarangModel barang);
    void updateBarang(BarangModel barang);
    BarangModel getBarangById(Long id);
    List<BarangModel> getListBarang();
}
