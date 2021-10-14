package apap.tugas.sielekthor.service;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.*;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

public interface PembelianService {
    List<PembelianModel> getListPembelian();
    PembelianModel getPembelianById(Long idPembelian);
    void addPembelian(PembelianModel pembelian);
    void deletePembelian(PembelianModel pembelian);
    void restokBarang(PembelianModel pembelian);
    String getNoInvoice(PembelianModel pembelian);
    Integer jumlah (HashMap<PembelianBarangModel, BarangModel> hashPembelianBarang);
}
