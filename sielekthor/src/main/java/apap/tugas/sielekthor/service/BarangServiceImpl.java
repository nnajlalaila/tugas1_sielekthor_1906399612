package apap.tugas.sielekthor.service;
import apap.tugas.sielekthor.model.PembelianModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import apap.tugas.sielekthor.repository.BarangDB;
import apap.tugas.sielekthor.model.BarangModel;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDB barangDB;

    @Override
    public void addBarang(BarangModel barang){
        barangDB.save(barang);
    }
    @Override
    public  void updateBarang(BarangModel barang){
        barangDB.save(barang);
    }

    @Override
    public List<BarangModel> getListBarang(){
        return barangDB.findAll();
    }

    @Override
    public BarangModel getBarangById(Long id){
        return barangDB.getBarangModelById(id);
    }

    @Override
    public List<BarangModel> cariBarang(String namaTipe, String stok){
        System.out.println("DI DALAM SERVICE");
        List<BarangModel> hasilCariBarang = new ArrayList<>();
        List<BarangModel> allBarang = getListBarang();
        System.out.println(stok);
        System.out.println(namaTipe);
        for (BarangModel barang :allBarang ) {
            if(barang.getTipe().getNama().equals(namaTipe)){
                System.out.println(stok);
                if(barang.getStok() > 0 && stok.equals("true")){
                    hasilCariBarang.add(barang);
                    System.out.println(stok);
                }

                if(barang.getStok() <= 0 && stok.equals("false")){
                    hasilCariBarang.add(barang);
                    System.out.println(stok);
                }
            }
        }
        return  hasilCariBarang;
    }

    @Override
    public List<BarangModel> cariBarangTipe(String namaTipe){
        List<BarangModel> hasilCariBarang = new ArrayList<>();
        List<BarangModel> allBarang = getListBarang();

        for (BarangModel barang :allBarang ) {
            if(barang.getTipe().getNama().equals(namaTipe)){
                hasilCariBarang.add(barang);
            }
        }
        return  hasilCariBarang;
    }

    @Override
    public  List<BarangModel> cariBarangStok(String stok) {

        List<BarangModel> hasilCariBarang = new ArrayList<>();
        List<BarangModel> allBarang = getListBarang();

        for (BarangModel barang : allBarang ) {
            if(barang.getStok() > 0 && stok.equals("true")){
                hasilCariBarang.add(barang);
            }

            if(barang.getStok() <= 0 && stok.equals("false")){
                hasilCariBarang.add(barang);
            }
        }
        return  hasilCariBarang;
    }


}



