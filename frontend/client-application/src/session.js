import axios from "axios";

export const createSession = async (tableId) => {
  try {
    const response = await axios.post(
      `http://localhost:8081/api/v1/session?tableId=${tableId}`
    );

    // store JWT locally
    localStorage.setItem("accessToken", response.data.accessToken);

    // return tableId from backend
    return response.data.tableId;
  } catch (err) {
    console.error("Failed to create session", err);
    throw err;
  }
};