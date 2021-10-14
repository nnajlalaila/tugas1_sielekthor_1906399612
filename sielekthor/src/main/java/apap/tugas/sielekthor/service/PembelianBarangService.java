package apap.tugas.sielekthor.service;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.model.BarangModel;
import java.util.List;
public interface PembelianBarangService {
    void addPembelianBarang(PembelianBarangModel pembelianBarang);
    List<PembelianBarangModel> getPembelianBarangList();
    PembelianBarangModel getPembelianBarangById(Long id);
    List<PembelianBarangModel> getListByPembelianAndBarang(PembelianModel pembelian, BarangModel barang);
}
