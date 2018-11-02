# webcrawler
Java code to crawl through a domain and its sub domain. It will show domain and sub domains attribute like image / document etc.

App.java - The nain class of this application, In there getPageLinks function which takes Website as a parameter that we want to crawl. We can crawl through this site and check each and every links and check whether it's link or subdomain.
Further it will check for attachment whether it's images or pdf etc. In this given code, we only extract image sources and check the details(Like image source path, image height & image width) for the same.

-------------------------------------------

public void getPageLinks(String URL) {

		Domain domain = new Domain();
		domain.setCustomerList(new ArrayList<SubDomain>());
		SubDomain subDomain = null;
		ImageInfo imageInfo = null;
		List<ImageInfo> imageInfoList = null;
		try {

			/*
			 * JSOUP lib to connec to geiven website and it's arrtibute
			 * like Images,documents (pdf/words/etc)
			 * */
			Document document = Jsoup.connect(URL).timeout(10 * 1000).ignoreContentType(true).get();
			Elements links = document.select("a[href]");
			logger.info("Total count of links are : " + links.size());
			for (Element link : links) {
				subDomain = new SubDomain();
				subDomain.setName(link.attr("abs:href"));

				if (null != link.attr("abs:href") && !link.attr("abs:href").equals(" ")
						&& link.attr("href").contains("prudential")
						&& link.attr("abs:href").matches("[http].+[^(pdf|rar|zip)]")) {

					imageInfoList = new ArrayList<ImageInfo>();
					String innerUrl = link.attr("abs:href");
					logger.info(innerUrl);
					Document innerDoc = Jsoup.connect(innerUrl).timeout(10 * 1000).ignoreContentType(true).get();
					Elements imgLinks = innerDoc.select("img[src]");
					logger.info("Total count of img links in link : " + imgLinks.size());
					for (Element innerLink : imgLinks) {
						imageInfo = new ImageInfo();
						imageInfo.setImageUrl(innerLink.attr(ApplicationConstants.APP_KEY_CONSTANTS.IMG_SRC));
						imageInfo.setHeight(innerLink.attr(ApplicationConstants.APP_KEY_CONSTANTS.IMG_HEIGHT));
						imageInfo.setWidth(innerLink.attr(ApplicationConstants.APP_KEY_CONSTANTS.IMG_WIDTH));
						imageInfoList.add(imageInfo);

					}
					subDomain.setImageInfo(imageInfoList);

				}

				domain.getCustomerList().add(subDomain);
			}
		}
    

    
   
