package com.rj.bd.tool;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import cn.hutool.core.lang.UUID;

/**
* @desc 
* 
* @author TianShuo
* 
* @version 2021年4月15日 下午4:59:12
*/
public class PutFile {
	private static String accessKey = "ZpBp-02v6F5Bod37EldpUAtfVHL-manH2t4tIAbk";
	private static String secretKey = "pEmx2uT-51KTXaQsOvWaI0bg_4moH_wnL8p3jZ38";
	private static String bucket = "funnystories";


	/**
	 * @desc 上传文件
	 * @param ImgIO  InputStream
	 * @param suffix 文件后缀
	 * @return
	 */
	public static String Putimgs(InputStream ImgIO, String suffix) {
		Configuration cfg = new Configuration();
		UploadManager uploadManager = new UploadManager(cfg);

		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = UUID.randomUUID().toString() + System.currentTimeMillis() + "." + suffix;
		try {
			// 设置key认证
			Auth auth = Auth.create(accessKey, secretKey);

			// 获取仓库名
			String upToken = auth.uploadToken(bucket);

			try {
				//
				// 上传
				Response response = uploadManager.put(ImgIO, key, upToken, null, null);
				// 解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				System.out.println(putRet.key);

				// System.out.println(putRet.hash);
				// 返回文件名
				return "http://file.dreamoforiginal.cn/" + putRet.key;
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					// ignore
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}


}
