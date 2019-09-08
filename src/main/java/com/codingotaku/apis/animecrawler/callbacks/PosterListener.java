package com.codingotaku.apis.animecrawler.callbacks;

import com.codingotaku.apis.animecrawler.Result;

@FunctionalInterface
public interface PosterListener {

	public void loaded(String synopsys, Result result);
}
