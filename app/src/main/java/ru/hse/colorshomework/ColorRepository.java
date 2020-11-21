package ru.hse.colorshomework;

import java.util.ArrayList;
import java.util.List;

public class ColorRepository {
    public static int DATA_SIZE = 3;

    private static volatile ColorRepository mInstance;

    public static ColorRepository getInstance() {
        if (mInstance == null) {
            synchronized (ColorRepository.class) {
                if (mInstance == null) {
                    mInstance = new ColorRepository();
                }
            }
        }
        return mInstance;
    }

    protected final List<ColorItem> mData;

    public ColorRepository() {
        mData = initializeData();
    }

    public ColorItem item(int index) {
        return mData.get(index);
    }

    public List<ColorItem> list() {
        return mData;
    }

    // Функция инициализации списка цветов
    protected List<ColorItem> initializeData() {
        final List<ColorItem> data = new ArrayList<>();
        for (int position = 0; position < DATA_SIZE; position ++) {
            data.add(new ColorItem(position+1));
        }
        return data;
    }
}
