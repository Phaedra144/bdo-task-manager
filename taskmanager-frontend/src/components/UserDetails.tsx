import { useEffect, useState } from 'react';
import { Button, Col, Form, Row } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { getUserById, updateUser } from '../api/TaskManagerApiService';
import { User } from '../types/UserTasksTypes';

export const UserDetails = () => {
  const location = useLocation();
  const params = useParams();
  const queryParams = new URLSearchParams(location.search);
  const navigate = useNavigate();

  const [user, setUser] = useState<User>();

  const userId = Number.parseInt(params.userId || '0');
  const notModifiable = Boolean(queryParams.get('no-modify'));

  useEffect(() => {
    getUser();
  }, [userId]);

  const getUser = () => {
    if (userId) {
      getUserById(userId)
        .then((response) => {
          setUser(response.data);
          reset({
            fullName: response.data.fullName,
            email: response.data.email,
            address: response.data.address,
          });
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  const initialValues = {
    fullName: '',
    email: '',
    address: {
      city: '',
      zip: '',
      street: '',
      streetNumber: '',
    },
  };

  const {
    register,
    formState: { errors },
    reset,
    handleSubmit,
  } = useForm({
    mode: 'onTouched',
    reValidateMode: 'onChange',
    defaultValues: initialValues,
  });

  const onSubmit = (data: typeof initialValues) => {
    const updatedUser: User = {
      ...data,
      id: userId,
      tasks: user?.tasks || [],
    };
    updateUser(updatedUser);
    navigate('/users');
  };

  return (
    <div className="container w-75">
      <h1>User details</h1>
      <Form onSubmit={handleSubmit(onSubmit)}>
        <Row className="align-items-center mt-4">
          <Col className="col-sm-2">
            <Form.Label>Full name</Form.Label>
          </Col>
          <Col className="col-sm-10">
            <Form.Control
              className={errors.fullName ? 'is-invalid' : ''}
              type="text"
              disabled={notModifiable}
              {...register('fullName', {
                required: 'Name is required',
              })}
            />
          </Col>
        </Row>
        {errors.fullName && (
          <Form.Text className="text-danger">
            {errors.fullName.message}
          </Form.Text>
        )}
        <Row className="align-items-center mt-4">
          <Col className="col-sm-2">
            <Form.Label>Email</Form.Label>
          </Col>
          <Col className="col-sm-10">
            <Form.Control
              className={errors.email ? 'is-invalid' : ''}
              type="email"
              disabled={notModifiable}
              {...register('email', {
                required: 'Email is required',
              })}
            />
          </Col>
        </Row>
        {errors.email && (
          <Form.Text className="text-danger">{errors.email.message}</Form.Text>
        )}
        <Row className="align-items-center mt-4">
          <Col className="col-sm-2">
            <Form.Label>City</Form.Label>
          </Col>
          <Col className="col-sm-10">
            <Form.Control
              className={errors.address?.city ? 'is-invalid' : ''}
              type="text"
              disabled={notModifiable}
              {...register('address.city')}
            />
          </Col>
        </Row>
        <Row className="align-items-center mt-4">
          <Col className="col-sm-2">
            <Form.Label>Zip</Form.Label>
          </Col>
          <Col className="col-sm-10">
            <Form.Control
              className={errors.address?.zip ? 'is-invalid' : ''}
              type="text"
              disabled={notModifiable}
              {...register('address.zip')}
            />
          </Col>
        </Row>
        <Row className="align-items-center mt-4">
          <Col className="col-sm-2">
            <Form.Label>Street</Form.Label>
          </Col>
          <Col className="col-sm-10">
            <Form.Control
              className={errors.address?.street ? 'is-invalid' : ''}
              type="text"
              disabled={notModifiable}
              {...register('address.street')}
            />
          </Col>
        </Row>
        <Row className="align-items-center mt-4">
          <Col className="col-sm-2">
            <Form.Label>Street number</Form.Label>
          </Col>
          <Col className="col-sm-10">
            <Form.Control
              className={errors.address?.streetNumber ? 'is-invalid' : ''}
              type="number"
              disabled={notModifiable}
              {...register('address.streetNumber')}
            />
          </Col>
        </Row>
        {!notModifiable && (
          <Button className="mt-5" type="submit">
            Update
          </Button>
        )}
      </Form>
    </div>
  );
};
