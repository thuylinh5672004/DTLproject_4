using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Daothithuylinhproject2_2210900036.Areas.ModelView
{
    public class CartItem
    {
        public string MaSP { get; set; }

        // Tên sản phẩm, độ dài tối đa là 50 ký tự
        public string TenSP { get; set; }

        // Mã danh mục, độ dài tối đa là 10 ký tự
        public string MaDM { get; set; }

        // Giá bán của sản phẩm (ví dụ: 99999.99)
        public decimal GiaBan { get; set; }

        // Số lượng còn trong kho
        public int SoLuongTonKho { get; set; }

        // Dữ liệu ảnh (có thể là đường dẫn ảnh)
        public string Anh { get; set; }

        // Số lượng sản phẩm được thêm vào giỏ hàng
        public int SoLuong { get; set; }

        // Tính tổng giá trị cho sản phẩm này (giá bán * số lượng)
        public decimal ThanhTien => SoLuong * GiaBan;

        // Constructor mặc định
        public CartItem()
        {
        }

        // Constructor với các tham số để khởi tạo đối tượng CartItem
        public CartItem(string maSP, string tenSP, string maDM, decimal giaBan, int soLuongTonKho, string anh, int soLuong)
        {
            MaSP = maSP;
            TenSP = tenSP;
            MaDM = maDM;
            GiaBan = giaBan;
            SoLuongTonKho = soLuongTonKho;
            Anh = anh;
            SoLuong = soLuong;
        }
    }
    // ShoppingCart class
    public class ShoppingCart
    {
        // Danh sách các sản phẩm trong giỏ hàng
        public List<CartItem> Items { get; set; } = new List<CartItem>();

        // Thêm sản phẩm vào giỏ hàng
        public void AddItem(CartItem newItem)
        {
            // Kiểm tra xem sản phẩm này đã có trong giỏ hàng hay chưa
            var existingItem = Items.FirstOrDefault(item => item.MaSP == newItem.MaSP);

            if (existingItem != null)
            {
                // Nếu sản phẩm đã tồn tại, tăng số lượng
                existingItem.SoLuong += newItem.SoLuong;
            }
            else
            {
                // Nếu sản phẩm chưa tồn tại, thêm vào giỏ hàng
                Items.Add(newItem);
            }
        }

        // Xóa sản phẩm khỏi giỏ hàng theo mã sản phẩm
        public void RemoveItem(string maSP)
        {
            var itemToRemove = Items.FirstOrDefault(item => item.MaSP == maSP);
            if (itemToRemove != null)
            {
                Items.Remove(itemToRemove);
            }
        }

        // Cập nhật số lượng sản phẩm trong giỏ hàng
        public void UpdateToCart(string maSP, int soLuongMoi)
        {
            var existingItem = Items.FirstOrDefault(item => item.MaSP == maSP);
            if (existingItem != null)
            {
                if (soLuongMoi <= 0)
                {
                    // Nếu số lượng mới là 0 hoặc nhỏ hơn, xóa sản phẩm khỏi giỏ hàng
                    Items.Remove(existingItem);
                }
                else
                {
                    // Cập nhật số lượng mới
                    existingItem.SoLuong = soLuongMoi;
                }
            }
        }

        // Tính tổng tiền giỏ hàng
        public decimal GetTotalAmount()
        {
            return Items.Sum(item => item.ThanhTien);
        }

        // Kiểm tra giỏ hàng có trống hay không
        public bool IsEmpty()
        {
            return !Items.Any();
        }

        // Xóa toàn bộ giỏ hàng
        public void ClearCart()
        {
            Items.Clear();
        }

    }
}