//package com.example.cafebackend.util;
//
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//@Component
//public class StartUtil implements ApplicationListener<ApplicationReadyEvent> {
//
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        File fileFolderUpload = new File(FileUtil.folderPath);
//        if (! fileFolderUpload.exists()){
//            fileFolderUpload.mkdir();
//        }
//
////        File fileFolderProduct = new File(FileUtil.imageProductUrl);
////        if (! fileFolderProduct.exists()){
////            fileFolderProduct.mkdir();
////        }
//    }
//}
