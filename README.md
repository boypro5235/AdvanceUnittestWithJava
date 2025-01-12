# Thuật toán Dijkstra với Unit Test trong Spring Boot
## Tổng quan
Dự án này triển khai thuật toán tìm đường ngắn nhất Dijkstra bằng Java, sử dụng Spring Boot để quản lý ngữ cảnh ứng dụng và các dependency. Chương trình cũng bao gồm các bài kiểm tra đơn vị (unit test) toàn diện để kiểm tra tính chính xác của thuật toán trong nhiều tình huống khác nhau.

## Tính năng
Tính toán đường đi ngắn nhất: Tìm đường ngắn nhất từ một nút xuất phát đến tất cả các nút khác trong đồ thị.
Xử lý lỗi: Quản lý các trường hợp đặc biệt như trọng số âm, đồ thị trống và nút bắt đầu không hợp lệ.
Kiểm tra đơn vị (Unit Tests): Xác minh thuật toán với nhiều trường hợp thử nghiệm khác nhau, bao gồm đồ thị nhỏ, đồ thị lớn và các trường hợp đặc biệt.

## Yêu cầu
**Để xây dựng và chạy dự án, cần cài đặt:**

Java 8 hoặc phiên bản mới hơn
Maven
Spring Boot
## Cấu trúc dự án
css
src  
├── main  
│   ├── java  
│   │   └── com.example.AdvanceUnittestWithJava  
│   │       ├── DijkstraAlgorithm.java  
│   └── resources  
│       └── application.properties  
├── test  
│   ├── java  
│   │   └── com.example.AdvanceUnittestWithJava  
│   │       ├── DijkstraAlgorithmTest.java  
DijkstraAlgorithm.java
Lớp này triển khai logic cốt lõi của thuật toán Dijkstra.

Đầu vào: Đồ thị được biểu diễn dưới dạng một map của các nút, mỗi nút chứa các nút kề và trọng số của các cạnh.
Đầu ra: Một map chứa khoảng cách ngắn nhất từ nút bắt đầu đến tất cả các nút khác.
**Xử lý lỗi:**
Ném ra ngoại lệ IllegalArgumentException nếu đồ thị có trọng số âm.
Ném ra ngoại lệ IllegalArgumentException nếu nút bắt đầu không có trong đồ thị.
DijkstraAlgorithmTest.java
Lớp này chứa các bài kiểm tra đơn vị để đảm bảo tính chính xác và độ ổn định của thuật toán.

**Các trường hợp kiểm tra**
Đồ thị cơ bản: Kiểm tra thuật toán với một đồ thị nhỏ và so sánh đường đi ngắn nhất dự kiến.
Trọng số âm: Đảm bảo thuật toán ném ra ngoại lệ khi gặp trọng số âm.
Đồ thị trống: Xác nhận thuật toán xử lý đồ thị trống một cách hợp lý.
Đồ thị lớn: Kiểm tra hiệu suất và tính chính xác với đồ thị chứa 1.000 nút.
Nút bắt đầu không hợp lệ: Kiểm tra hành vi khi nút bắt đầu không có trong đồ thị.

## Ví dụ sử dụng
**Đầu vào đồ thị:**

java

Map<String, Map<String, Integer>> graph = new HashMap<>();  
graph.put("A", Map.of("B", 1, "C", 4));  
graph.put("B", Map.of("A", 1, "C", 2, "D", 5));  
graph.put("C", Map.of("A", 4, "B", 2, "D", 1));  
graph.put("D", Map.of("B", 5, "C", 1));  
**Đầu ra:**

Các đường đi ngắn nhất từ nút A:  
A: 0  
B: 1  
C: 3  
D: 4  
## Các tình huống lỗi
Trọng số âm: Ném ra IllegalArgumentException với thông báo: Graph contains negative weight.
Nút bắt đầu không hợp lệ: Ném ra IllegalArgumentException với thông báo: Start node not found in graph.
Đồ thị trống: Ném ra IllegalArgumentException.

