package com.atguigu.userprofile.service.impl;

import com.atguigu.userprofile.bean.FileInfo;
import com.atguigu.userprofile.service.FileInfoService;
import com.atguigu.userprofile.service.HdfsService;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@DS("mysql")
public class HdfsServiceImpl  implements HdfsService {


    @Value("${hdfs.url}")
    private String hdfsUrl;
    @Value("${hdfs.username}")
    private String hdfsUserName;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public Long createFile(String path, MultipartFile file)  {

        path+= UUID.randomUUID().toString()+"/"  ;
        try {
            if (StringUtils.isEmpty(path) || null == file.getBytes()) {
                return -1L;
            }
            String fileName = file.getOriginalFilename();
            FileSystem fs = getFileSystem();

            // 上传时默认当前目录，后面自动拼接文件的目录
            Path newPath = new Path(path+ fileName);
            // 打开一个输出流
            FSDataOutputStream outputStream = fs.create(newPath);
            outputStream.write(file.getBytes());
            outputStream.close();
            fs.close();
        }catch ( Exception e){
            throw new RuntimeException("上传hdfs失败");
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(file.getOriginalFilename());
        String fileExName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        fileInfo.setFileExName(fileExName);
        fileInfo.setCreateTime(new Date());
        fileInfo.setFilePath(hdfsUrl+"/"+ path  + fileInfo.getFileName());
        fileInfo.setFileSystem("HDFS");
        fileInfo.setFileStatus(1L);

        fileInfoService.save(fileInfo);
        return fileInfo.getId();
    }

    /**
     * 获取HDFS文件系统对象
     * @return
     * @throws Exception
     */
    public   FileSystem getFileSystem() throws Exception {
        // 客户端去操作hdfs时是有一个用户身份的，默认情况下hdfs客户端api会从jvm中获取一个参数作为自己的用户身份
        // DHADOOP_USER_NAME=hadoop
        // 也可以在构造客户端fs对象时，通过参数传递进去
        FileSystem fileSystem = FileSystem.get(new URI(hdfsUrl), getConfiguration(), hdfsUserName);
        return fileSystem;
    }


    /**
     * 获取HDFS配置信息
     * @return
     */
    private   Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        configuration.set("fs.defaultFS", hdfsUrl);
        return configuration;
    }
}
