package apap.tugas.sielekthor.service;
import apap.tugas.sielekthor.model.BarangModel;
import java.util.List;

import apap.tugas.sielekthor.model.PembelianModel;
import org.springframework.stereotype.Service;

public interface BarangService {
    void addBarang(BarangModel barang);
    void updateBarang(BarangModel barang);
    BarangModel getBarangById(Long id);
    List<BarangModel> getListBarang();
    List<BarangModel> cariBarang(String namaTipe, String stok);
    List<BarangModel> cariBarangTipe(String namaTipe);
    List<BarangModel> cariBarangStok( String stok);
}
