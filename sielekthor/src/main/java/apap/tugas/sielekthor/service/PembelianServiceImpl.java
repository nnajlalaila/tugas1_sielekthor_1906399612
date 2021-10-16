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
    public String generateNoInvoice(PembelianModel pembelian) {
        String namaMember = pembelian.getMember().getNamaMember().toUpperCase();
        int tmp = ((int) namaMember.charAt(0)) - 64;
        String intFirst = Integer.toString(tmp);
        String namaAdmin = pembelian.getNamaAdmin().toUpperCase();
        Date tanggalPembelian= pembelian.getTanggalPembelian();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String format = formatter.format(tanggalPembelian);
        int bulanTahun = Integer.parseInt(format.substring(0,2)) + Integer.parseInt(format.substring(3,5));
        int siap = bulanTahun * 5;
        String export = Integer.toString(siap);

        if(export.length() ==  2){
            export = "0" + export;
        }
        Boolean pembayaran = pembelian.getIsCash();
        String random = RandomString.getAlphaNumericString(2);
        String noInvoice;
        String noPembayaran;
        if(pembayaran){
            noPembayaran = "02";
        }else {
            noPembayaran = "01";
        }

        System.out.println(namaMember);
        System.out.println(namaAdmin);
        noInvoice = intFirst.substring(0, 1) + namaAdmin.toUpperCase().charAt(namaAdmin.length()-1)
                + format.substring(0,2) + format.substring(3,5) + noPembayaran+ export+ random;

        return noInvoice;
    }

    @Override
    public void restokBarang(PembelianModel pembelian) {
        for (PembelianBarangModel pb: pembelian.getListPembelianBarang()) {
            pb.getBarang().setStok(pb.getBarang().getStok() + pb.getQuantity());
        }
    }

    @Override
    public List<PembelianModel> cariPembelian(Long idMember,boolean isCash) {
        List<PembelianModel> hasilCariPembelian = new ArrayList<>();
        List<PembelianModel> allPembelian = getListPembelian();

        for (PembelianModel pembelian :allPembelian) {
            if(pembelian.getMember().getId().equals(idMember)){
                if(pembelian.getIsCash().equals(isCash)){
                    hasilCariPembelian.add(pembelian);
                }
            }
        }
        return  hasilCariPembelian;
    }

    @Override
    public List<PembelianModel> cariPembelianIsCash(boolean isCash) {
        List<PembelianModel> hasilCariPembelian = new ArrayList<>();
        List<PembelianModel> allPembelian = getListPembelian();

        for (PembelianModel pembelian : allPembelian) {
            if(pembelian.getIsCash().equals(isCash)){
                    hasilCariPembelian.add(pembelian);
            }
        }
        return  hasilCariPembelian;
    }

    @Override
    public List<PembelianModel> cariPembelianIdMember(Long idMember) {
        List<PembelianModel> hasilCariPembelian = new ArrayList<>();
        List<PembelianModel> allPembelian = getListPembelian();

        for (PembelianModel pembelian : allPembelian) {
            if(pembelian.getMember().getId().equals(idMember)){
                hasilCariPembelian.add(pembelian);
            }
        }
        return  hasilCariPembelian;
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
