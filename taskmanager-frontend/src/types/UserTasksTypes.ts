export interface User {
    id: number;
    fullName: string;
    email: string;
    city: string;
    zip: string;
    street: string;
    streetNumber: string;
    tasks: Task[];
}

export interface Task {
    id: number;
    title: string;
    description: string;
    userId: number;
}
