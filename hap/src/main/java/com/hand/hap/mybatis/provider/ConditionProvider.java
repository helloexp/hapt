/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.hand.hap.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import com.hand.hap.mybatis.mapperhelper.MapperHelper;
import com.hand.hap.mybatis.mapperhelper.MapperTemplate;

/**
 * ConditionProvider实现类，基础方法实现类
 *
 * @author liuzh
 */
public class ConditionProvider extends MapperTemplate {

    private ExampleProvider exampleProvider;

    public ConditionProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
        exampleProvider = new ExampleProvider(mapperClass, mapperHelper);
    }

    /**
     * 根据Condition查询总数
     *
     * @param ms
     * @return
     */
    public String selectCountByCondition(MappedStatement ms) {
        return exampleProvider.selectCountByExample(ms);
    }

    /**
     * 根据Condition删除
     *
     * @param ms
     * @return
     */
    public String deleteByCondition(MappedStatement ms) {
        return exampleProvider.deleteByExample(ms);
    }


    /**
     * 根据Condition查询
     *
     * @param ms
     * @return
     */
    public String selectByCondition(MappedStatement ms) {
        return exampleProvider.selectByExample(ms);
    }

    /**
     * 根据Condition查询
     *
     * @param ms
     * @return
     */
    public String selectByConditionAndRowBounds(MappedStatement ms) {
        return exampleProvider.selectByExample(ms);
    }

    /**
     * 根据Example更新非null字段
     *
     * @param ms
     * @return
     */
    public String updateByConditionSelective(MappedStatement ms) {
        return exampleProvider.updateByExampleSelective(ms);
    }

    /**
     * 根据Condition更新
     *
     * @param ms
     * @return
     */
    public String updateByCondition(MappedStatement ms) {
        return exampleProvider.updateByExample(ms);
    }
}