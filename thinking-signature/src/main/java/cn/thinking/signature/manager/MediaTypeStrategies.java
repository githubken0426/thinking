package cn.thinking.signature.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import cn.thinking.signature.pojo.SignatureData;
import cn.thinking.signature.service.DefaultSignatureService;

@Service
public class MediaTypeStrategies {
	private static final Logger logger = LoggerFactory.getLogger(DefaultSignatureService.class);

	@Service
	public class DefaultMediaTypeStrategy extends MediaTypeBaseStrategy {

		@Override
		public List<MediaType> getMediaTypes() {
			logger.info("DefaultMediaTypeStrategy");
			return null;
		}

		@Override
		protected void doHandle(SignatureData requestData, HttpServletRequest httpRequest) throws Exception {
			logger.info("DefaultMediaTypeStrategy doHandle");
		}

	}

	@Service
	public class JSONMediaTypeStrategy extends MediaTypeBaseStrategy {

		@Override
		public List<MediaType> getMediaTypes() {
			List<MediaType> list = new ArrayList<>();
			list.add(MediaType.APPLICATION_JSON_UTF8);
			return list;
		}

		@Override
		protected void doHandle(SignatureData requestData, HttpServletRequest httpRequest) throws Exception {
			logger.info("JsonMediaTypeStrategy doHandle");
		}

	}
	
	@Service
	public class XMLMediaTypeStrategy extends MediaTypeBaseStrategy {

		@Override
		public List<MediaType> getMediaTypes() {
			List<MediaType> list = new ArrayList<>();
			list.add(MediaType.APPLICATION_XML);
			return list;
		}

		@Override
		protected void doHandle(SignatureData requestData, HttpServletRequest httpRequest) throws Exception {
			logger.info("XMLMediaTypeStrategy doHandle");
		}

	}
}
