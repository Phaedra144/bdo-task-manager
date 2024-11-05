import { Nullable } from "./common";

export interface User {
  id: number;
  fullName: Nullable<string>;
  email: Nullable<string>;
  address: Nullable<Address>;
  tasks: Task[];
}

export interface Address {
  city: Nullable<string>;
  zip: Nullable<string>;
  street: Nullable<string>;
  streetNumber: Nullable<string>;
}

export interface Task {
  id: number;
  title: Nullable<string>;
  description: Nullable<string>;
  userId: number;
}
