import React from 'react';

const Footer = () => {
  return (
    <footer style={styles.footer}>
      <p>&copy; 2025 Nhà thuốc Online. All rights reserved.</p>
      <p>Liên hệ: 000000000 | Email: support@thuoconline.vn</p>
    </footer>
  );
};

const styles = {
  footer: {
    backgroundColor: '#343a40',
    color: 'white',
    padding: '20px',
    textAlign: 'center',
    marginTop: '40px'
  }
};

export default Footer;
