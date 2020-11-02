package com.example.myboot2.upload;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author YUANCHENGMAN
 * @date 2020-10-21
 */
public class EasyExcelUtils {

    /**
     * 默认一次性操作的最大行数
     */
    private static final int DEFAULT_MAX_ROW = 1000;

    /**
     * 获取读取excel的监听器
     *
     * @param doConsumer 处理数据的处理器
     * @param <T>
     * @return
     */
    public static <T extends UploadDataBase> AnalysisEventListener<T> getListener(Consumer<List<T>> doConsumer) {
        return EasyExcelUtils.getListener(doConsumer, null, DEFAULT_MAX_ROW);
    }

    /**
     * 获取读取excel的监听器，数据全部校验完成后，再写入。
     *
     * @param dataHandlerConsumer 处理数据的处理器
     * @param dataCheckFunc       校验数据的处理器
     * @param <T>
     * @return
     */
    public static <T extends UploadDataBase> AnalysisEventListener<T> getListener(Consumer<List<T>> dataHandlerConsumer, Function<List<T>, Boolean> dataCheckFunc) {
        return EasyExcelUtils.getListener(dataHandlerConsumer, dataCheckFunc, DEFAULT_MAX_ROW);
    }

    /**
     * 获取读取excel的监听器，数据全部校验完成后，再写入。
     *
     * @param dataHandlerConsumer 处理数据的处理器
     * @param dataCheckFunc       校验数据的处理器
     * @param maxRow              每次写入的最大数量
     * @param <T>
     * @return
     */
    public static <T extends UploadDataBase> AnalysisEventListener<T> getListener(Consumer<List<T>> dataHandlerConsumer, Function<List<T>, Boolean> dataCheckFunc, int maxRow) {
        return getAnalysisEventListener(dataHandlerConsumer, dataCheckFunc, maxRow);
    }

    public static <T extends UploadDataBase> AnalysisEventListener<T> getListener2(Consumer<List<T>> dataHandlerConsumer, Function<T, Boolean> dataCheckFunc) {
        return EasyExcelUtils.getListener2(dataHandlerConsumer, dataCheckFunc, DEFAULT_MAX_ROW);
    }

    public static <T extends UploadDataBase> AnalysisEventListener<T> getListener2(Consumer<List<T>> dataHandlerConsumer, Function<T, Boolean> dataCheckFunc, int maxRow) {
        return getAnalysisEventListener2(dataHandlerConsumer, dataCheckFunc, maxRow);
    }

    /**
     * 数据全部校验完后，按批次保存
     *
     * @param dataHandlerConsumer 数据处理器
     * @param dataCheckFunc       数据校验器，一次性全部校验
     * @param maxRow              允许一次性保存数据的最大行数
     * @param <T>
     * @return
     */
    private static <T extends UploadDataBase> AnalysisEventListener<T> getAnalysisEventListener(Consumer<List<T>> dataHandlerConsumer, Function<List<T>, Boolean> dataCheckFunc, int maxRow) {
        return new AnalysisEventListener<T>() {
            private ArrayList<T> list = new ArrayList<>();

            @Override
            public void invoke(T t, AnalysisContext analysisContext) {
                Integer rowIndex = analysisContext.readRowHolder().getRowIndex();
                t.setRowIndex(rowIndex + 1);
                list.add(t);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                if (list.isEmpty()) {
                    return;
                }

                // 校验数据
                if (dataCheckFunc == null || dataCheckFunc.apply(list)) {
                    // 执行数据操作
                    if (dataHandlerConsumer != null) {
                        int count = (int) Math.ceil((double) list.size() / maxRow);
                        for (int i = 0; i < count; i++) {
                            int fromIndex = i * maxRow;
                            int toIndex = fromIndex + maxRow;
                            if (toIndex > list.size()) {
                                toIndex = list.size();
                            }
                            List<T> saveList = list.subList(fromIndex, toIndex);
                            dataHandlerConsumer.accept(saveList);
                        }
                        list.clear();
                    }
                }
            }
        };
    }

    /**
     * 一条条校验数据，达到指定条数后，若没有错误，将该批次数据保存；若校验有错，则清空集合重新往下读取。
     *
     * @param dataHandlerConsumer 数据处理器
     * @param dataCheckFunc       数据校验器，一条条校验
     * @param maxRow              允许一次性保存数据的最大行数
     * @param <T>
     * @return
     */
    private static <T extends UploadDataBase> AnalysisEventListener<T> getAnalysisEventListener2(Consumer<List<T>> dataHandlerConsumer, Function<T, Boolean> dataCheckFunc, int maxRow) {
        return new AnalysisEventListener<T>() {
            private ArrayList<T> list = new ArrayList<>();

            @Override
            public void invoke(T t, AnalysisContext analysisContext) {
                Integer rowIndex = analysisContext.readRowHolder().getRowIndex();
                t.setRowIndex(rowIndex + 1);
                if (dataCheckFunc == null) {
                    list.add(t);
                    return;
                }
                if (dataCheckFunc.apply(t)) {
                    list.add(t);
                    if (list.size() > maxRow) {
                        if (dataHandlerConsumer != null) {
                            dataHandlerConsumer.accept(list);
                        }
                        list.clear();
                    }
                } else {
                    list.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                if (list.isEmpty()) {
                    return;
                }

                // 执行数据操作
                if (dataHandlerConsumer != null) {
                    dataHandlerConsumer.accept(list);
                    list.clear();
                }
            }
        };
    }

    public static <T extends UploadDataBase> void handle(File file, int headRowNumber, Consumer<List<T>> dataHandler, Function<List<T>, Boolean> dataCheckFunc, Class<T> clazz) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(file);
        EasyExcelUtils.handle(inputStream, 0, headRowNumber, dataHandler, dataCheckFunc, clazz);
    }

    public static <T extends UploadDataBase> void handle(File file, int sheetNo, int headRowNumber, Consumer<List<T>> dataHandler, Function<List<T>, Boolean> dataCheckFunc, Class<T> clazz) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(file);
        EasyExcelUtils.handle(inputStream, sheetNo, headRowNumber, dataHandler, dataCheckFunc, clazz);
    }

    public static <T extends UploadDataBase> void handle(InputStream inputStream, int headRowNumber, Consumer<List<T>> dataHandler, Function<List<T>, Boolean> dataCheckFunc, Class<T> clazz) {
        EasyExcelUtils.handle(inputStream, 0, headRowNumber, dataHandler, dataCheckFunc, clazz);
    }

    public static <T extends UploadDataBase> void handle(InputStream inputStream, int sheetNo, int headRowNumber, Consumer<List<T>> dataHandler, Function<List<T>, Boolean> dataCheckFunc, Class<T> clazz) {
        AnalysisEventListener<T> listener = EasyExcelUtils.getListener(dataHandler, dataCheckFunc);
        EasyExcel.read(inputStream, clazz, listener).sheet(sheetNo).headRowNumber(headRowNumber).doRead();
    }

    public static <T extends UploadDataBase> void handle2(InputStream inputStream, int headRowNumber, Consumer<List<T>> dataHandler, Function<T, Boolean> dataCheckFunc, Class<T> clazz) {
        EasyExcelUtils.handle2(inputStream, 0, headRowNumber, dataHandler, dataCheckFunc, clazz);
    }

    public static <T extends UploadDataBase> void handle2(InputStream inputStream, int sheetNo, int headRowNumber, Consumer<List<T>> dataHandler, Function<T, Boolean> dataCheckFunc, Class<T> clazz) {
        AnalysisEventListener<T> listener = EasyExcelUtils.getListener2(dataHandler, dataCheckFunc);
        EasyExcel.read(inputStream, clazz, listener).sheet(sheetNo).headRowNumber(headRowNumber).doRead();
    }
}
