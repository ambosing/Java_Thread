package readwrite;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


// ReadLock 여러 개의 스레드가 한꺼번게 공유자원에 접근 가능
// Writer 락은 공유 자원에 한 스레드만 접근 가능
// 그래서 ReadLock과 WriteLock으로 나눠 주는 것이 성능에 더 좋음
// 성능 : CPU - M1 Pro
// Lock : 1446ms
// ReadLock, WriterLock : 396ms
public class Main {
    public static final int HIGHEST_PRICE = 1000;

    public static void main(String[] args) throws InterruptedException {
        InventoryDatabase inventoryDatabase = new InventoryDatabase();

        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
        }

        Thread writer = new Thread(() -> {
            while (true) {
                inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
                inventoryDatabase.removeItem(random.nextInt(HIGHEST_PRICE));

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }
            }
        });

        writer.setDaemon(true);
        writer.start();

        int numberOfReaderThreads = 7;
        List<Thread> readers = new ArrayList<>();

        for (int readerIndex = 0; readerIndex < numberOfReaderThreads; readerIndex++) {
            Thread reader = new Thread(() -> {
                for (int i = 0; i < 100000; i++) {
                    int upperBoundPrice = random.nextInt(HIGHEST_PRICE);
                    int lowerBoundPrice = upperBoundPrice > 0 ? random.nextInt(upperBoundPrice) : 0;
                    inventoryDatabase.getNumberOfItemsInPriceRance(lowerBoundPrice, upperBoundPrice);
                }
            });

            reader.setDaemon(true);
            readers.add(reader);
        }

        long startReadingTime = System.currentTimeMillis();

        for (Thread reader : readers) {
            reader.start();
        }

        for (Thread reader : readers) {
            reader.join();
        }

        long endReadingTime = System.currentTimeMillis();

        System.out.println(String.format("Reading took %d ms", endReadingTime - startReadingTime));
    }

    public static class InventoryDatabase {
        private TreeMap<Integer, Integer> priceToCountMap = new TreeMap<>();
        private Lock lock = new ReentrantLock();
        private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        private Lock readLock = reentrantReadWriteLock.readLock();
        private Lock writeLock = reentrantReadWriteLock.writeLock();

        public int getNumberOfItemsInPriceRance(int lowerBound, int upperBound) {
//            lock.lock();
            readLock.lock();
            try {
                Integer fromKey = priceToCountMap.ceilingKey(lowerBound);
                Integer toKey = priceToCountMap.floorKey(upperBound);

                if (fromKey == null || toKey == null) {
                    return 0;
                }

                NavigableMap<Integer, Integer> rangeOfPrices = priceToCountMap.subMap(fromKey, true, toKey, true);

                int sum = 0;
                for (Integer numberOfItemsForPrice : rangeOfPrices.values()) {
                    sum += numberOfItemsForPrice;
                }

                return sum;
            } finally {
//                lock.unlock();
                readLock.unlock();
            }

        }

        public void addItem(int price) {
//            lock.lock();
            writeLock.lock();
            try {
                Integer numberOfItemsForPrice = priceToCountMap.get(price);
                if (numberOfItemsForPrice == null) {
                    priceToCountMap.put(price, 1);
                } else {
                    priceToCountMap.put(price, numberOfItemsForPrice + 1);
                }

            } finally {
//                lock.unlock();
                writeLock.unlock();
            }
        }

        public void removeItem(int price) {
//            lock.lock();
            writeLock.lock();
            try {
                Integer numberOfItemsForPrice = priceToCountMap.get(price);
                if (numberOfItemsForPrice == null || numberOfItemsForPrice == 1) {
                    priceToCountMap.remove(price);
                } else {
                    priceToCountMap.put(price, numberOfItemsForPrice - 1);
                }
            } finally {
//                lock.unlock();
                writeLock.unlock();
            }

        }
    }
}
