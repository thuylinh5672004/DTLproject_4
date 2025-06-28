import React from 'react';

const Banner = () => {
  return (
    <div style={styles.banner}>
      <h1>Chào mừng đến với Nhà thuốc Online</h1>
      <p>Mua thuốc dễ dàng - Uy tín - Nhanh chóng</p>
    </div>
  );
};

const styles = {
  banner: {
    backgroundImage: 'url("https://medipharusa.com/wp-content/uploads/2020/03/mau-quay-thuoc-dat-chuan-GPP-6-1.jpg")',
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    color: 'white',
    padding: '60px 20px',
    textAlign: 'center'
  }
};

export default Banner;
