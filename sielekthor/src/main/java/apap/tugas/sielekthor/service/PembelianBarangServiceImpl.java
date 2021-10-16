package apap.tugas.sielekthor.service;
import apap.tugas.sielekthor.model.*;
import apap.tugas.sielekthor.repository.BarangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import apap.tugas.sielekthor.repository.PembelianBarangDB;
import apap.tugas.sielekthor.model.PembelianBarangModel;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class PembelianBarangServiceImpl implements PembelianBarangService{
    @Autowired
    PembelianBarangDB pembelianBarangDB;

    @Override
    public void addPembelianBarang(PembelianBarangModel pembelianBarang){
        pembelianBarangDB.save(pembelianBarang);
    }

    @Override
    public List<PembelianBarangModel> getPembelianBarangList(){

        return pembelianBarangDB.findAll();
    }

    @Override
    public PembelianBarangModel getPembelianBarangById(Long id){
        return pembelianBarangDB.getPembelianBarangById(id);
    }

    @Override
    public List<PembelianBarangModel> getListByPembelianAndBarang(PembelianModel pembelian, BarangModel barang) {
        return pembelianBarangDB.findAllByPembelianAndBarang(pembelian,barang);
    }

}

