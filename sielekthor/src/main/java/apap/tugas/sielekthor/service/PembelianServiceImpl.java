package apap.tugas.sielekthor.service;
import apap.tugas.sielekthor.model.*;
import apap.tugas.sielekthor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import apap.tugas.sielekthor.repository.PembelianDB;
import apap.tugas.sielekthor.model.PembelianModel;
import java.util.List;

import java.time.LocalDate;
import java.util.*;
import java.text.SimpleDateFormat;
@Service
@Transactional
public class PembelianServiceImpl implements PembelianService {
    @Autowired
    PembelianDB pembelianDB;

    @Override
    public void addPembelian(PembelianModel pembelian){
        pembelianDB.save(pembelian);
    }

    @Override
    public void deletePembelian(PembelianModel pembelian){ pembelianDB.delete(pembelian);
    }

    @Override
    public List<PembelianModel> getListPembelian(){
        return pembelianDB.findAll();
    }

    @Override
    public PembelianModel getPembelianById(Long idPembelian){
        return pembelianDB.getPembelianModelById(idPembelian);
    }

    @Override
    public String getNoInvoice(PembelianModel pembelian) {
        String namaMember = pembelian.getMember().getNamaMember().toUpperCase();
        int tmp = ((int) namaMember.charAt(0)) - 64;
        String intFirst = Integer.toString(tmp);
        String namaAdmin = pembelian.getNamaAdmin();
        Date tanggalPembelian= pembelian.getTanggalPembelian();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String format = formatter.format(tanggalPembelian);
        Boolean pembayaran = pembelian.getIsCash();
        String random = RandomString.getAlphaNumericString(2);
        String noInvoice;
        String noPembayaran;
        if(pembayaran){
            noPembayaran = "02";
        }else {
            noPembayaran = "01";
        }

        noInvoice = intFirst.substring(0, 1) + namaAdmin.toUpperCase().charAt(namaAdmin.length()-1)
                + format.substring(0,2) + format.substring(3,5) + noPembayaran+ random;

        return noInvoice;
    }

    @Override
    public void restokBarang(PembelianModel pembelian) {
        for (PembelianBarangModel pb: pembelian.getListPembelianBarang()) {
            pb.getBarang().setStok(pb.getBarang().getStok() + pb.getQuantity());
        }
    }

    @Override
    public Integer jumlah (HashMap<PembelianBarangModel, BarangModel> hashPembelianBarang){
        int total = 0;
        for (PembelianBarangModel i : hashPembelianBarang.keySet()) {
            total += i.getQuantity() * hashPembelianBarang.get(i).getHargaBarang();
        }
        return total;
    }

    public static class RandomString {
        // function to generate a random string of length n
        static String getAlphaNumericString(int n)
        {
            // chose a Character random from this String
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            // create StringBuffer size of AlphaNumericString
            StringBuilder sb = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index = (int)(AlphaNumericString.length() * Math.random());
                // add Character one by one in end of sb
                sb.append(AlphaNumericString.charAt(index));
            }
            return sb.toString();

        }}
}
