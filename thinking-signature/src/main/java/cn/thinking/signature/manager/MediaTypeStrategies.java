package cn.thinking.signature.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import cn.thinking.signature.pojo.SignatureData;

@Service
public class MediaTypeStrategies {
	@Service
	public class DefaultMediaTypeStrategy extends MediaTypeBaseStrategy{

		@Override
		public List<MediaType> getMediaTypes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void doHandle(SignatureData requestData, HttpServletRequest httpRequest) throws Exception {
			// TODO Auto-generated method stub
			
		}
		 
	 }
    
}
