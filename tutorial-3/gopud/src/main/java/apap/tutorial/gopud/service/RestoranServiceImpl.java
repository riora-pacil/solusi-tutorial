package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.RestoranDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class RestoranServiceImpl implements RestoranService{
    @Autowired
    private RestoranDb restoranDb;

    @Override
    public void addRestoran(RestoranModel restoran) {
        restoranDb.save(restoran);
    }

    @Override
    public List<RestoranModel> getRestoranList() {
        return restoranDb.findAllByOrderByNamaAsc();
    }

    @Override
    public RestoranModel getRestoranByIdRestoran(Long idRestoran) {
        try{
            Optional<RestoranModel> restoran = restoranDb.findByIdRestoran(idRestoran);
            return restoran.get();
        }catch (NoSuchElementException e) {
            throw e;
        }
    }

    @Override
    public RestoranModel changeRestoran(RestoranModel restoranModel) {
        try{
            // mengambil object restoran yang ingin diubah
            RestoranModel targetRestoran = getRestoranByIdRestoran(restoranModel.getIdRestoran());
            targetRestoran.setNama(restoranModel.getNama());
            targetRestoran.setAlamat(restoranModel.getAlamat());
            targetRestoran.setNomorTelepon(restoranModel.getNomorTelepon());
            restoranDb.save(targetRestoran);
            return targetRestoran;
        } catch (NullPointerException nullException) {
            throw nullException;
        }
    }

    @Override
    public void deleteRestoran(Long idRestoran) {
        RestoranModel restoran = getRestoranByIdRestoran(idRestoran);
        restoranDb.delete(restoran);
    }
}
