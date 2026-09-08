# Metro Launcher (kiểu Windows Phone 10)

Đây là **project mã nguồn Android Studio đầy đủ** cho một app launcher (màn hình chính thay thế)
theo phong cách "Live Tile" của Windows Phone 10 / Windows 10 Mobile:

- Màn hình chính hiển thị lưới ô vuông màu (tile) 4 cột, mỗi ô là 1 app đã cài, kèm đồng hồ lớn phía trên.
- Nhấn "tất cả ứng dụng »" để mở danh sách toàn bộ app theo A-Z, có ô tìm kiếm (giống App List của WP10).
- Đăng ký làm launcher thật (bắt sự kiện phím Home), phím Back sẽ đưa app xuống nền thay vì thoát.
- `minSdk 24`, `targetSdk 34` → chạy tốt trên hầu hết máy Android 7 trở lên, **bao gồm Infinix Note 30**
  (chạy Android 12/13 với giao diện XOS).

## ⚠️ Vì sao mình gửi mã nguồn chứ không phải file .apk luôn?

Máy chủ mình dùng để chạy lệnh **không có Android SDK/công cụ build (aapt2, d8, apksigner...) và
không có kết nối internet** để tải các công cụ đó về. Vì vậy mình không thể tự biên dịch (compile)
và ký (sign) ra file `.apk` thật ngay tại đây. Thay vào đó, đây là cách nhanh nhất để bạn có file
APK thật trong tay — chọn 1 trong 2 cách dưới đây, cách nào cũng chỉ mất vài phút:

## Cách 1 — Dùng Android Studio (khuyên dùng nếu có máy tính)

1. Cài [Android Studio](https://developer.android.com/studio) (miễn phí).
2. Mở Android Studio → **Open** → chọn thư mục `WP10Launcher` (thư mục vừa giải nén).
3. Đợi Android Studio tự động tải Gradle + SDK và "Sync" xong (cần internet, chỉ lần đầu).
4. Vào menu **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
5. File `app-debug.apk` sẽ nằm ở: `app/build/outputs/apk/debug/app-debug.apk`.
6. Copy file này vào điện thoại (Infinix Note 30) và cài đặt (nhớ bật "Cài từ nguồn không xác định").

## Cách 2 — Không có Android Studio? Dùng GitHub Actions (tự động, miễn phí)

Project này đã có sẵn file `.github/workflows/build-apk.yml`:

1. Tạo 1 repository mới trên GitHub, đẩy (push) toàn bộ thư mục `WP10Launcher` lên đó.
2. Vào tab **Actions** trên GitHub → workflow "Build Debug APK" sẽ tự chạy.
3. Chờ khoảng 2-3 phút, vào **Actions → lần chạy mới nhất → phần Artifacts** để tải file
   `WP10Launcher-debug-apk.zip` — giải nén ra là file `app-debug.apk` thật, cài được ngay.

## Sau khi cài vào Infinix Note 30

Vào **Cài đặt → Ứng dụng → Ứng dụng mặc định → Ứng dụng màn hình chính (Home app)** → chọn
**Metro Launcher** để đặt làm launcher chính thay cho XOS Launcher gốc.

## Có thể tuỳ chỉnh thêm

- Đổi tên app: sửa `app_name` trong `res/values/strings.xml`.
- Đổi số app được ghim ở màn hình chính: sửa số `20` trong `MainActivity.kt` (dòng `apps.take(20)`).
- Đổi bảng màu tile: sửa `res/values/colors.xml`.
