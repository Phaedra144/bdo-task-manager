import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
});

export const getUsers = () => {
  return apiClient.get('/users');
};

export const getUserById = (userId: number) => {
  return apiClient.get(`/users/${userId}`);
};

export const getTasksByUserid = (userId: number) => {
  return apiClient.get(`/users/${userId}/tasks`);
};
