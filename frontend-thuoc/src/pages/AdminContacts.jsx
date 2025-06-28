import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function AdminContacts() {
  const [contacts, setContacts] = useState([]);
  const [replyMap, setReplyMap] = useState({});

  useEffect(() => {
    fetchContacts();
  }, []);

  const fetchContacts = async () => {
    try {
      const res = await axios.get('http://localhost:3000/api/lienhe');
      setContacts(res.data);
    } catch (err) {
      console.error('❌ Lỗi tải danh sách liên hệ:', err);
    }
  };

  const handleReply = async (id) => {
    const traloi = replyMap[id];
    if (!traloi) return alert('Vui lòng nhập nội dung trả lời');

    try {
      await axios.put(`http://localhost:3000/api/lienhe/${id}/traloi`, { traloi });
      alert('✅ Đã gửi phản hồi');
      fetchContacts();
    } catch (err) {
      console.error('❌ Lỗi khi trả lời:', err);
      alert('❌ Trả lời thất bại');
    }
  };

  return (
    <div className="container mt-4">
      <h2>📨 Danh sách liên hệ</h2>
      {contacts.length === 0 ? <p>Không có liên hệ nào.</p> : (
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>Họ tên</th>
              <th>Email</th>
              <th>Nội dung</th>
              <th>Trạng thái</th>
              <th>Trả lời</th>
            </tr>
          </thead>
          <tbody>
            {contacts.map((c) => (
              <tr key={c.id}>
                <td>{c.ten}</td>
                <td>{c.email}</td>
                <td>{c.noidung}</td>
                <td>{c.trangthai}</td>
                <td>
                  {c.trangthai === 'Đã trả lời' ? (
                    <div><strong>Đã trả lời:</strong><br />{c.traloi}</div>
                  ) : (
                    <>
                      <textarea
                        className="form-control"
                        rows="2"
                        placeholder="Nội dung trả lời..."
                        value={replyMap[c.id] || ''}
                        onChange={(e) =>
                          setReplyMap({ ...replyMap, [c.id]: e.target.value })
                        }
                      />
                      <button
                        className="btn btn-primary mt-1"
                        onClick={() => handleReply(c.id)}
                      >
                        Gửi phản hồi
                      </button>
                    </>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
// This component fetches and displays a list of contact requests from the server.