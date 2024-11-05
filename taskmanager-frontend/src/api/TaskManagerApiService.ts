import axios from 'axios';
import { User } from '../types/userTasksTypes';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
});

export const getUsers = () => {
  return apiClient.get('/users');
};

export const getUserById = (userId: number) => {
  return apiClient.get(`/users/${userId}`);
};

export const getTasksByUserId = (userId: number) => {
  return apiClient.get(`/users/${userId}/tasks`);
};

export const updateUser = (user: User) => {
  return apiClient.put(`/users`, user);
};

export const deleteUserById = (userId: number) => {
  return apiClient.delete(`/users/${userId}`);
};
