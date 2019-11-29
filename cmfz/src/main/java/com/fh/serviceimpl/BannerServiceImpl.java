package com.fh.serviceimpl;

import com.fh.dao.BannerDao;
import com.fh.entity.Banner;
import com.fh.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Banner> selectAllBanner() {
        return bannerDao.selectAllBanner();
    }

    @Override
    public void insertBanner(Banner banner) {
        banner.setTime(new java.util.Date());
        bannerDao.insertBanner(banner);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Banner selectOneBanner(String id) {
        return bannerDao.selectOneBanner(id);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerDao.updateBanner(banner);
    }

    @Override
    public void deleteOneBanner(String id) {
        bannerDao.deleteOneBanner(id);
    }

    @Override
    public void updateBannerImg(String id, String img) {
        bannerDao.updateBannerImg(id,img);
    }

    @Override
    public void deleteSomeBanner(String[] ids) {
        bannerDao.deleteSomeBanner(ids);
    }
}
