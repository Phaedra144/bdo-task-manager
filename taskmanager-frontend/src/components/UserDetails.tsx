import { Button, Col, Form, Row } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import { useLocation } from 'react-router-dom';

export const UserDetails = () => {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);

  const notModifiable = queryParams.get('no-modify');

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
  } = useForm({
    mode: 'onTouched',
    reValidateMode: 'onChange',
    defaultValues: initialValues,
  });

  return (
    <div className="container w-75">
      <h1>User details</h1>
      <Form>
        <Row className="align-items-center mt-4">
          <Col className="col-sm-2">
            <Form.Label>Full name</Form.Label>
          </Col>
          <Col className="col-sm-10">
            <Form.Control
              className={errors.fullName ? 'is-invalid' : ''}
              type="text"
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
              className={errors.fullName ? 'is-invalid' : ''}
              type="email"
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
            <Form.Label>Address</Form.Label>
          </Col>
          <Col className="col-sm-10">
            <Form.Control
              className={errors.address ? 'is-invalid' : ''}
              type="text"
              {...register('address')}
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
