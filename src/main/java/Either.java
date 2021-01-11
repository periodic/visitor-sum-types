abstract class Either<A, B> {
    public interface EitherVisitor<A, B, C> {
        C left(A a);
        C right(B b);
    }

    public abstract <C> C accept(EitherVisitor<A, B, C> visitor);

    public static <A, B>Either<A, B> Right(B b) {
        return new Either<A, B>() {
            public <C> C accept(EitherVisitor<A, B, C> visitor) {
                return visitor.right(b);
            }
        };
    }

    public static <A, B>Either<A, B> Left(A a) {
        return new Either<A, B>() {
            public <C>C accept(EitherVisitor<A, B, C> visitor) {
                return visitor.left(a);
            }
        };
    }

    public static <A, B> boolean isLeft(Either<A, B> either) {
        return either.accept(new EitherVisitor<A, B, Boolean>() {
            public Boolean left(A a) {
                return true;
            }
            public Boolean right(B b) {
                return false;
            }
        });
    }

    public static <A, B> Boolean isRight(Either<A, B> either) {
        return either.accept(new EitherVisitor<A, B, Boolean>() {
            public Boolean left(A a) {
                return false;
            }
            public Boolean right(B b) {
                return true;
            }
        });
    }

    public static <A, B> String show(Either<A, B> either) {
        return either.accept(new EitherVisitor<A, B, String>() {
            public String left(A a) {
                return "Left(" + a.toString() + ")";
            }
            public String right(B b) {
                return "Right(" + b.toString() + ")";
            }
        });
    }


    public static void main(String[] argv) {
        Either<String, String> lefty = Left("Left");
        Either<String, String> righty = Right("Right");

        System.out.println(show(lefty));
        System.out.println(show(righty));
    }
}