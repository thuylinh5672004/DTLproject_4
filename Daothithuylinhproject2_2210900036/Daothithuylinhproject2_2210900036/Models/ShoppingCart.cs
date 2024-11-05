
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Daothithuylinhproject2_2210900036.Models
{
    public class ShoppingCart
    {
        private List<CartItem> items = new List<CartItem>();  // Danh sách sản phẩm trong giỏ hàng

        // Thêm sản phẩm vào giỏ hàng
        public void AddItem(string maSP, string tenSP, decimal giaBan, int soLuong, string anh)
        {
            var existingItem = items.FirstOrDefault(i => i.MaSP == maSP);
            if (existingItem == null)
            {
                // Thêm mới sản phẩm vào giỏ hàng
                items.Add(new CartItem
                {
                    MaSP = maSP,
                    TenSP = tenSP,
                    GiaBan = giaBan,
                    SoLuong = soLuong,
                    Anh = anh,
                    ThanhTien = giaBan * soLuong  // Tính thành tiền cho sản phẩm mới
                });
            }
            else
            {
                // Cập nhật số lượng và thành tiền nếu sản phẩm đã có trong giỏ hàng
                existingItem.SoLuong += soLuong;
                existingItem.ThanhTien = existingItem.GiaBan * existingItem.SoLuong; // Cập nhật thành tiền
            }
        }

        // Xóa sản phẩm khỏi giỏ hàng
        public void RemoveItem(string maSP)
        {
            items.RemoveAll(i => i.MaSP == maSP);
        }

        // Cập nhật số lượng sản phẩm
        public void UpdateItemQuantity(string maSP, int newQuantity)
        {
            var item = items.FirstOrDefault(i => i.MaSP == maSP);
            if (item != null)
            {
                item.SoLuong = newQuantity;
                item.ThanhTien = item.GiaBan * newQuantity; // Cập nhật thành tiền

                if (item.SoLuong <= 0)
                {
                    RemoveItem(maSP);  // Xóa sản phẩm nếu số lượng bằng 0
                }
            }
        }

        // Tính tổng giá trị giỏ hàng
        public decimal GetTotal()
        {
            return items.Sum(i => i.ThanhTien); // Tính tổng theo thành tiền của từng sản phẩm
        }

        // Lấy danh sách sản phẩm trong giỏ hàng
        public List<CartItem> GetItems()
        {
            return items;
        }

        // Xóa tất cả sản phẩm trong giỏ hàng
        public void Clear()
        {
            items.Clear();
        }
    }
    // Định nghĩa lớp CartItem
    public class CartItem
    {
        public string MaSP { get; set; }      // Mã sản phẩm
        public string TenSP { get; set; }      // Tên sản phẩm
        public decimal GiaBan { get; set; }    // Giá bán
        public int SoLuong { get; set; }       // Số lượng sản phẩm trong giỏ hàng
        public string Anh { get; set; }        // Ảnh sản phẩm
        public decimal ThanhTien { get; set; } // Thành tiền
    }
}